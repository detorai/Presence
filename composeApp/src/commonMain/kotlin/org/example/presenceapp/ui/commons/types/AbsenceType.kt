package org.example.presenceapp.ui.commons.types

enum class AbsenceType(
    val reason: String,
    val requiresDocumentation: Boolean
) {
    SICK(
        reason = "Болезнь",
        requiresDocumentation = true
    ),
    COMPETITION(
        reason = "Участие в соревнованиях",
        requiresDocumentation = false
    ),
    ACADEMIC_LEAVE(
        reason = "Академический отпуск",
        requiresDocumentation = false
    ),
    FAMILY_REASONS(
        reason = "Семейные обстоятельства",
        requiresDocumentation = true
    ),
    INDIVIDUAL_PLAN(
        reason = "Индивидуальный план",
        requiresDocumentation = false
    )
}