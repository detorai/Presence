package org.example.presenceapp.data.common.dto.student

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import org.example.presenceapp.data.common.dto.presence.AttendanceTypeDto

@Serializable
data class StudentRequestDto(
    val attendanceTypeId: Int,
    val startAt: LocalDate,
    val endAt: LocalDate
)


