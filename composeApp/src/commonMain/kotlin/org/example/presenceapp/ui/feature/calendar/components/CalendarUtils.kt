package org.example.presenceapp.ui.feature.calendar.components

import kotlinx.datetime.*
import org.example.presenceapp.domain.entities.AttendanceType
import org.example.presenceapp.domain.entities.DayData
import kotlin.sequences.generateSequence

object CalendarUtils {
    fun generateMonthData(
        year: Int,
        month: Int,
        attendanceData: Map<LocalDate, AttendanceType>
    ): List<DayData> {
        val firstDay = LocalDate(year, month, 1)
        val lastDay = firstDay.plus(DatePeriod(months = 1)).minus(DatePeriod(days = 1))

        val startDate = firstDay.minus(DatePeriod(days = firstDay.dayOfWeek.isoDayNumber - 1))
        val endDate = lastDay.plus(DatePeriod(days = 7 - lastDay.dayOfWeek.isoDayNumber))

        return generateSequence(startDate) { it.plus(1, DateTimeUnit.DAY) }
            .takeWhile { it <= endDate }
            .map { date ->
                DayData(
                    date = date,
                    isCurrentMonth = date.monthNumber == month,
                    attendance = attendanceData[date]
                )
            }
            .toList()
    }

    private fun isLeapYear(year: Int): Boolean {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
    }
}