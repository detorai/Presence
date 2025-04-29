package org.example.presenceapp.domain.entities

import kotlinx.datetime.LocalDate

data class Students(
    val studentId: Int,
    val uuid: String,
    val email: String,
    val number: String,
    val fio: String,
    val enrollDate: LocalDate,
    val expulsionDate: LocalDate? = null,
)
