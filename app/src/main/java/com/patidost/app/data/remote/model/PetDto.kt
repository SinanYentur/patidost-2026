package com.patidost.app.data.remote.model

import com.patidost.app.data.model.PetEntity

data class PetDto(
    val id: String = "",
    val name: String = "",
    val breed: String = "",
    val age: Int = 0,
    val imageUrl: String = "",
    val description: String = ""
) {
    fun toEntity() = PetEntity(
        id = id,
        name = name,
        breed = breed,
        age = age,
        imageUrl = imageUrl,
        description = description,
        isAdopted = false
    )
}
