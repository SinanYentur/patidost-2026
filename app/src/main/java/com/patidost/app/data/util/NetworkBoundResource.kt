package com.patidost.app.data.util

import com.patidost.app.core.util.Resource
import com.patidost.app.presentation.ui.util.UiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

/**
 * A utility function that provides a resource from the local database,
 * but fetches from the network and saves to the database if the data is stale or not present.
 */
inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
    val data = query().first()

    if (shouldFetch(data)) {
        emit(Resource.Loading(data))

        try {
            saveFetchResult(fetch())
            emitAll(query().map { Resource.Success(it) })
        } catch (throwable: Throwable) {
            val errorMessage = UiText.DynamicString("Failed to fetch data: ${throwable.message}")
            emitAll(query().map { Resource.Error(errorMessage, it) })
        }
    } else {
        emitAll(query().map { Resource.Success(it) })
    }
}
