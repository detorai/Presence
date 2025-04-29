package org.example.presenceapp.data.common.dto.group

import kotlinx.datetime.LocalDate

data class StudentsResponseDto (
    val studentId: Int,
    val uuid: String,
    val email: String,
    val number: String,
    val fio: String,
    val enrollDate: LocalDate,
    val expulsionDate: LocalDate? = null
)