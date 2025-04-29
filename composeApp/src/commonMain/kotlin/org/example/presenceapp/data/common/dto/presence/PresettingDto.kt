package org.example.presenceapp.data.common.dto.presence

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class PresettingDto(
    val id: Int,
    val attendanceType: AttendanceTypeDto,
    val studentId: Int,
    val startAt: LocalDate,
    val endAt: LocalDate,
)