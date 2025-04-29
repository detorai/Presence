package org.example.presenceapp.domain.command

import kotlinx.datetime.LocalDate

data class PresenceCommand(
    val studentId: Int,
    val scheduleId: Int,
    val attendanceTypeId: Int,
    val presenceDate: LocalDate,
)
