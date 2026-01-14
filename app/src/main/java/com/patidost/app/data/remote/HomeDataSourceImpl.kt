package com.patidost.app.data.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.patidost.app.core.util.Resource
import com.patidost.app.data.mapper.toDomain
import com.patidost.app.data.remote.dto.PetDto
import com.patidost.app.data.remote.dto.TopGiverDto
import com.patidost.app.domain.model.TopGiver
import com.patidost.app.presentation.ui.util.UiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.util.Calendar
import javax.inject.Inject

class HomeDataSourceImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : HomeDataSource {

    override fun getFeaturedPets(): Flow<Resource<List<PetDto>>> = flow {
        emit(Resource.Loading())
        try {
            val snapshot = firestore.collection("pets")
                .orderBy("weeklyPatiCount", Query.Direction.DESCENDING)
                .limit(10)
                .get()
                .await()

            val petDtos = snapshot.documents.mapNotNull { doc ->
                doc.toObject(PetDto::class.java)?.apply { id = doc.id }
            }
            emit(Resource.Success(petDtos))
        } catch (e: Exception) {
            emit(Resource.Error(UiText.DynamicString(e.message ?: "An unknown error occurred.")))
        }
    }

    override fun getTopGivers(): Flow<Resource<List<TopGiver>>> = flow {
        emit(Resource.Loading())
        try {
            val cal = Calendar.getInstance()
            val weekId = "${cal.get(Calendar.YEAR)}-W${cal.get(Calendar.WEEK_OF_YEAR)}"

            val snapshot = firestore.collection("weekly_leaderboards").document(weekId).get().await()

            val topGiversDto = snapshot.toObject(TopGiverDto::class.java)
            val topGivers = topGiversDto?.users?.map { it.toDomain() } ?: emptyList()

            emit(Resource.Success(topGivers))
        } catch (e: Exception) {
            emit(Resource.Error(UiText.DynamicString(e.message ?: "An unknown error occurred.")))
        }
    }
}
