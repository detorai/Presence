package org.example.project.ui.weeks

import org.example.presenceapp.data.common.dto.auth.UserResponseDto
import org.example.presenceapp.domain.entities.Attendance
import org.example.presenceapp.domain.common.Student
import org.example.presenceapp.domain.entities.Schedule
import org.example.project.domain.models.MonthWithWeeks

data class WeeksScreenState(
    var error: String? = null,
    var success: Boolean = false,
    var data: List<MonthWithWeeks> = emptyList(),
    var userInfo: UserResponseDto? = null,
    var lessonsList: List<Schedule> = emptyList(),
    var groupList: List<Student> = emptyList(),
    var groupPresence: List<Attendance> = emptyList()
)
