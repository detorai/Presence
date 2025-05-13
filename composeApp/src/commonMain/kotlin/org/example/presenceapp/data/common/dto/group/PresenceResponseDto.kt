package org.example.presenceapp.data.common.dto.group

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class PresenceResponseDto(
    val presenceDate: LocalDate,
    val subjects : List<PresenceSubjectResponseDto>
)
@Serializable
data class PresenceSubjectResponseDto(
    val dayOfWeek: Int,
    val presenceRow: List<PresenceRowResponseDto>
)
@Serializable
data class PresenceRowResponseDto(
    val presenceId: Int,
    val schedule: PresenceRowScheduleResponseDto,
    val attendanceTypeId: Int,
    val studentId: Int
)
@Serializable
data class PresenceRowScheduleResponseDto(
    val id: Int,
    val lessonNumber: Int,
    val audience: String,
    val subject: SubjectResponseDto
)