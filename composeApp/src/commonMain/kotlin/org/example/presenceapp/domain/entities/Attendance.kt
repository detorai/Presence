package org.example.presenceapp.domain.entities

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class Attendance(
    val studentId: String,
    val date: LocalDate,
    val type: AttendanceType,
    val isModified: Boolean,
    val absenceReason: String? = null
)

@Serializable
enum class AttendanceType {
    PRESENT, ABSENT
}

@Serializable
enum class AbsenceReason {
    SKIP,
    SICK,
    COMPETITION,
    FAMILY_REASON
}