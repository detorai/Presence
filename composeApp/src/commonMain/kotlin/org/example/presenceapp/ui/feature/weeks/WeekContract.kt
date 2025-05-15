package org.example.project.ui.weeks

import org.example.presenceapp.ui.base.ViewEvent
import org.example.presenceapp.ui.base.ViewSideEffect
import org.example.presenceapp.ui.base.ViewState
import org.example.project.domain.models.MonthWithWeeks
import org.example.project.domain.models.Week

class WeekContract {

    sealed class Event: ViewEvent {
        data object LoadWeeks: Event()
        data class WeekSelected(val week: Week) : Event()
    }

    data class State(
        var error: String? = null,
        var success: Boolean = false,
        var data: List<MonthWithWeeks> = emptyList(),
    ): ViewState

    sealed class Effect: ViewSideEffect {
        sealed class Navigation : Effect() {
            data class ToSchedule(val week: Week) : Navigation()
        }
        data class ShowError(val message: String?) : Effect()
    }
}
