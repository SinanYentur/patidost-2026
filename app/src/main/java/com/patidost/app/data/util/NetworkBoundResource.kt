package com.patidost.app.data.util

import com.patidost.app.core.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

/**
 * A utility function that provides a resource from local database and updates it from network.
 * This is the implementation of the "Single Source of Truth" pattern.
 */
inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>, // Fetches data from the local database
    crossinline fetch: suspend () -> RequestType, // Fetches data from the network
    crossinline saveFetchResult: suspend (RequestType) -> Unit, // Saves network data to the database
    crossinline shouldFetch: (ResultType) -> Boolean = { true } // Decides if a network fetch should be made
) = flow {
    // 1. Emit initial data from the database
    val data = query().first()
    emit(Resource.Loading(data))

    // 2. Decide whether to fetch from network
    val flow = if (shouldFetch(data)) {
        // 3. Fetch succeeded, save to DB and emit from DB
        try {
            saveFetchResult(fetch())
            query().map { Resource.Success(it) }
        } catch (throwable: Throwable) {
            // 4. Fetch failed, emit error and keep old data
            query().map { Resource.Error(throwable.message, it) }
        }
    } else {
        // 5. Not fetching from network, just emit data from DB
        query().map { Resource.Success(it) }
    }

    // Emit the combined flow
    emitAll(flow)
}
