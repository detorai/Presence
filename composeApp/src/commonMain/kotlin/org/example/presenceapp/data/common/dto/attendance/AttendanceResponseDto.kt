package org.example.presenceapp.data.common.dto.attendance

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class AttendanceResponseDto(
    val presenceId: Int,
    val scheduleId: Int,
    val attendanceTypeId: Int,
    val presenceDate: LocalDate,
    val studentId: Int
)