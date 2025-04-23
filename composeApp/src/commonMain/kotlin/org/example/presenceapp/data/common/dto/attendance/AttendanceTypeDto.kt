package org.example.presenceapp.data.common.dto.attendance

import kotlinx.serialization.Serializable

@Serializable
data class AttendanceTypeDto(
    val id: Int,
    val name: String
)
