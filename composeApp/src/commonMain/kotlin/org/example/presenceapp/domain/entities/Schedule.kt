package org.example.presenceapp.domain.entities

data class Schedule(
    val dayOfWeek: Int,
    val scheduleInfo: ScheduleInfo
)
data class ScheduleInfo(
    val id: Int,
    val lessonNumber: Int,
    val audience: String,
    val subject: Subject,
)
data class Subject(
    val id: Int,
    val name: String
)


