package org.example.project.domain.models

import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month

data class MonthWithWeeks(
    val month: Month,
    val year: Int,
    val weeks: List<Week>
)

data class Week(
    val startDate: LocalDate,
    val endDate: LocalDate,
)

