package org.example.presenceapp.domain.entities

import kotlinx.datetime.LocalDate

data class Presence(
    val presenceDate: LocalDate,
    val subjects : List<PresenceSubject>
)
data class PresenceSubject(
    val dayOfWeek: Int,
    val presenceRow: List<PresenceRow>
)

data class PresenceRow(
    val presenceId: Int,
    val schedule: PresenceRowSchedule,
    val attendanceTypeId: Int,
    val studentId: Int
)
data class PresenceRowSchedule(
    val id: Int,
    val lessonNumber: Int,
    val audience: String,
    val subject: Subject
)

