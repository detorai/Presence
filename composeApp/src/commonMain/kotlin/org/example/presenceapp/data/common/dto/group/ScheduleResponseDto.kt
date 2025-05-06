package org.example.presenceapp.data.common.dto.group

import kotlinx.serialization.Serializable


@Serializable
data class ScheduleResponseDto(
    val dayOfWeek: Int,
    val subjects: List<SubjectsResponseDto>
)

@Serializable
data class SubjectsResponseDto(
    val id: Int,
    val lessonNumber: Int,
    val audience: String,
    val subject: SubjectResponseDto,
    val dayOfWeek: Int,
)

@Serializable
data class
SubjectResponseDto(
    val id: Int,
    val name: String
)