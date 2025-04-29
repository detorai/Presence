package org.example.presenceapp.domain.entities

import kotlinx.datetime.LocalDate
import org.example.presenceapp.data.common.dto.presence.AttendanceTypeDto

data class Presetting(
    val id: Int,
    val attendanceType: AttendanceTypeDto,
    val studentId: Int,
    val startAt: LocalDate,
    val endAt: LocalDate
)
