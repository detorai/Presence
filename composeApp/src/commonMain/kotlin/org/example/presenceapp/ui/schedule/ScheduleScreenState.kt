package org.example.presenceapp.ui.schedule

import kotlinx.datetime.LocalDate
import org.example.presenceapp.domain.entities.Attendance
import org.example.presenceapp.domain.common.Student
import org.example.presenceapp.domain.entities.Schedule
import org.example.project.domain.models.Week

data class ScheduleScreenState(
    var currentWeek: Week? = null,
    val selectedDate: LocalDate? = null,
    var schedule: Schedule? = null,
    var isUserSwiping: Boolean = false,
    var lessonsList: List<Schedule> = emptyList(),
    var groupList: List<Student> = emptyList(),
    var groupPresence: List<Attendance> = emptyList()
)
