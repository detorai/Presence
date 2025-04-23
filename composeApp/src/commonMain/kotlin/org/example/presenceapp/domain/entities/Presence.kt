package org.example.presenceapp.domain.entities

import kotlinx.datetime.LocalDate

data class Presence(
    val presenceId: Int,
    val scheduleId: Int,
    val attendanceTypeId: Int,
    val presenceDate: LocalDate,
    val studentId: Int
)
