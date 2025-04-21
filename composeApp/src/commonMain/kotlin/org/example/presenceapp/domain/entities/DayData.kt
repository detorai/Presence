package org.example.presenceapp.domain.entities

import kotlinx.datetime.LocalDate

data class DayData(
    val date: LocalDate,
    val isCurrentMonth: Boolean,
    val attendance: AttendanceType?
)