package org.example.presenceapp.ui.feature.attendance.components

import org.example.presenceapp.domain.entities.AbsenceReason

fun AbsenceReason.toCustomString(): String {
    return when (this) {
        AbsenceReason.SKIP -> "отсут"
        AbsenceReason.SICK -> "болезнь"
        AbsenceReason.COMPETITION -> "соревнования"
        AbsenceReason.FAMILY_REASON -> "семейн. обс-ва"
    }
}

fun String.toReadableString(): String {
    return try {
        AbsenceReason.valueOf(this).toCustomString()
    } catch (e: IllegalArgumentException) {
        this
    }
}