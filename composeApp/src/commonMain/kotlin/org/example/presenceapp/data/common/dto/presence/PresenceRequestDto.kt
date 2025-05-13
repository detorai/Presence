package org.example.presenceapp.data.common.dto.presence

import kotlinx.datetime.LocalDate

data class PresenceRequestDto(
    val studentId: Int,
    val scheduleId: Int,
    val attendanceTypeId: Int,
    val presenceDate: LocalDate
)
