package org.example.project.ui.login

import org.example.presenceapp.domain.common.Student
import org.example.presenceapp.domain.entities.Attendance
import org.example.presenceapp.domain.entities.Schedule
import org.example.presenceapp.domain.entities.UserResponse

data class LoginScreenState(
    var login: String = "",
    var password: String = "",
    var error: String? = null,
    var success: Boolean = false,
    var getAllData: Boolean = false,
    var check: Boolean = false,
    var userInfo: UserResponse? = null,
    var lessonsList: List<Schedule> = emptyList(),
    var groupList: List<Student> = emptyList(),
    var groupPresence: List<Attendance> = emptyList()
)
