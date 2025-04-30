package org.example.project.ui.weeks

import org.example.project.domain.models.MonthWithWeeks

data class WeeksScreenState(
    var error: String? = null,
    var success: Boolean = false,
    var data: List<MonthWithWeeks> = emptyList(),
)
