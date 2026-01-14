package com.patidost.app.data.mapper

import com.patidost.app.data.remote.dto.ReportDto
import com.patidost.app.domain.model.Report
import java.util.Date

fun ReportDto.toDomain(): Report {
    return Report(
        id = id,
        targetType = targetType,
        targetId = targetId,
        reporterId = reporterId,
        reason = reason,
        createdAt = createdAt ?: Date()
    )
}

fun Report.toDto(): ReportDto {
    return ReportDto(
        id = id,
        targetType = targetType,
        targetId = targetId,
        reporterId = reporterId,
        reason = reason,
        createdAt = createdAt
    )
}
