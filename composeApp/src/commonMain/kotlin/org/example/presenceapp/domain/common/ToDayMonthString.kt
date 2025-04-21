package org.example.project.domain.models

import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.number


fun Week.formatWeek(): String {
    val start = "${startDate.dayOfMonth.toString().padStart(2, '0')}.${startDate.month.number.toString().padStart(2, '0')}"
    val end = "${endDate.dayOfMonth.toString().padStart(2, '0')}.${endDate.month.number.toString().padStart(2, '0')}"
    return "$start - $end"
}


val russianMonths = mapOf(
    Month.JANUARY to "Январь",
    Month.FEBRUARY to "Февраль",
    Month.MARCH to "Март",
    Month.APRIL to "Апрель",
    Month.MAY to "Май",
    Month.JUNE to "Июнь",
    Month.JULY to "Июль",
    Month.AUGUST to "Август",
    Month.SEPTEMBER to "Сентябрь",
    Month.OCTOBER to "Октябрь",
    Month.NOVEMBER to "Ноябрь",
    Month.DECEMBER to "Декабрь"
)

fun Month.toRussianName(): String = russianMonths[this] ?: name


fun LocalDate.formatDay(): String {
    val currentDay = "${dayOfMonth.toString().padStart(2, '0')}.${month.number.toString().padStart(2, '0')}"
    return currentDay
}