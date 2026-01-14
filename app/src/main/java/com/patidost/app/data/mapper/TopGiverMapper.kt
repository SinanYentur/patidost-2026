package com.patidost.app.data.mapper

import com.patidost.app.data.remote.dto.TopGiverUserDto
import com.patidost.app.domain.model.TopGiver

fun TopGiverUserDto.toDomain(): TopGiver {
    return TopGiver(
        userId = this.userId,
        name = this.name,
        avatarUrl = this.avatarUrl,
        patiPointsGiven = this.weeklyPatiGiven
    )
}
