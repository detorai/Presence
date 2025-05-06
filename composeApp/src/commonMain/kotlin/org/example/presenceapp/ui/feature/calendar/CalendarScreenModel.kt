package org.example.presenceapp.ui.feature.calendar

import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.flow.*
import kotlinx.datetime.*
import org.example.presenceapp.domain.entities.AttendanceType
import org.example.presenceapp.domain.entities.DayData
import org.example.presenceapp.ui.feature.calendar.components.CalendarUtils

class CalendarScreenModel: ScreenModel {
    private val _monthData = MutableStateFlow<List<DayData>>(emptyList())
    val monthData: StateFlow<List<DayData>> = _monthData.asStateFlow()

    fun loadMonthData(year: Int, month: Int) {
        _monthData.value = CalendarUtils.generateMonthData(
            year = year,
            month = month,
            attendanceData = attendanceData()
        )
    }

    fun toggleAttendance(date: LocalDate) {
        val currentStatus = _monthData.value
            .firstOrNull { it.date == date }
            ?.attendance

        val newStatus = when (currentStatus) {
            null -> AttendanceType.ABSENT
            AttendanceType.ABSENT -> AttendanceType.PRESENT
            AttendanceType.PRESENT -> null
        }

        _monthData.update { days ->
            days.map { day ->
                if (day.date == date) day.copy(attendance = newStatus)
                else day
            }
        }
    }

    private fun attendanceData(): Map<LocalDate, AttendanceType> {
        return mapOf(
            LocalDate(2025, 3, 7) to AttendanceType.ABSENT,
            LocalDate(2025, 3, 8) to AttendanceType.PRESENT
        )
    }
}