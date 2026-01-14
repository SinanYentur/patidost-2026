package com.patidost.app.data.mapper

import com.patidost.app.data.remote.dto.PetDto
import com.patidost.app.domain.model.Pet
import com.patidost.app.domain.model.PetOwner
import java.util.Calendar
import java.util.Date

/**
 * Converts a PetDto from the data layer to a Pet in the domain layer.
 */
fun PetDto.toDomain(owner: PetOwner): Pet {
    return Pet(
        id = this.id,
        name = this.name,
        breed = this.breed,
        imageUrl = this.photoUrl ?: "",
        age = this.birthDate?.toAge() ?: 0,
        patiPoints = this.totalPati,
        owner = owner
    )
}

/**
 * Converts a Pet from the domain layer to a PetDto for the data layer.
 */
fun Pet.toDto(): PetDto {
    return PetDto(
        ownerId = this.owner.ownerId,
        name = this.name,
        breed = this.breed,
        photoUrl = this.imageUrl,
        birthDate = this.age.toBirthDate(),
        totalPati = this.patiPoints
        // Non-domain fields like species, gender, etc., are not part of the Pet model
        // and would need to be handled separately if required for creation.
    )
}

private fun Date.toAge(): Int {
    val birthCal = Calendar.getInstance()
    birthCal.time = this
    val todayCal = Calendar.getInstance()

    var age = todayCal.get(Calendar.YEAR) - birthCal.get(Calendar.YEAR)
    if (todayCal.get(Calendar.DAY_OF_YEAR) < birthCal.get(Calendar.DAY_OF_YEAR)) {
        age--
    }
    return age
}

private fun Int.toBirthDate(): Date {
    val cal = Calendar.getInstance()
    cal.add(Calendar.YEAR, -this)
    return cal.time
}
