package org.example.presenceapp.data.common.dto.group

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class PresenceResponseDto (
    val presenceId: Int,
    val scheduleId: Int,
    val attendanceTypeId: Int,
    val presenceDate: LocalDate,
    val studentId: Int
)