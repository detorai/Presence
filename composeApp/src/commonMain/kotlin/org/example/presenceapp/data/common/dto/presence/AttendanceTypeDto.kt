package org.example.presenceapp.data.common.dto.presence

import kotlinx.serialization.Serializable

@Serializable
data class AttendanceTypeDto(
    val id: Int,
    val name: String
)
