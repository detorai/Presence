package org.example.presenceapp.ui.feature.schedule

import kotlinx.datetime.LocalDate
import org.example.presenceapp.domain.entities.Schedule
import org.example.presenceapp.domain.entities.ScheduleInfo
import org.example.presenceapp.ui.base.ViewEvent
import org.example.presenceapp.ui.base.ViewSideEffect
import org.example.presenceapp.ui.base.ViewState
import org.example.project.domain.models.Week

class ScheduleContract {

    sealed class Event: ViewEvent {
        data class UpdateSelectedDay(val index: Int) : Event()
        data class SelectLesson(val lesson: ScheduleInfo?): Event()
    }

    data class State(
        var success: Boolean = false,
        var error: String? = null,
        val selectedDate: LocalDate? = null,
        val currentDayIndex: Int = 0,
        val lessons: Map<Int, List<ScheduleInfo>> = emptyMap(),
        val weeks: Week? = null

    ): ViewState

    sealed class Effect: ViewSideEffect {
        sealed class Navigation : Effect() {
            data class ToPresence(val lesson: ScheduleInfo?) : Navigation()
            data object ToBack: Navigation()
        }
        data class ShowError(val message: String?) : Effect()
        data class SelectedPageChanged(val currentDayIndex: Int) : Effect()
    }
}
