package org.example.presenceapp.domain.entities

data class Schedule(
    val id: Int,
    val lessonNumber: Int,
    val audience: String,
    val subject: Subject,
    val dayOfWeek: Int,
)
data class Subject(
    val id: Int,
    val name: String
)


