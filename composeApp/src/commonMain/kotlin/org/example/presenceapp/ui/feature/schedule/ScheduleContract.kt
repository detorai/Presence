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
        data class SetSwipeState(val swiping: Boolean) : Event()
        data class SelectDay(val index: Int) : Event()
        data class SelectLesson(val lesson: ScheduleInfo) : Event()
        data class UpdateSelectedDay(val index: Int, val isManual: Boolean) : Event()
        data class UpdateScrollState(val isScrolling: Boolean) : Event()
        data object SubmitSchedule : Event()
    }

    data class State(
        var success: Boolean = false,
        var error: String? = null,
        var currentWeek: Week? = null,
        val selectedDate: LocalDate? = null,
        var schedule: ScheduleInfo? = null,
        var isUserSwiping: Boolean = false,
        val currentDayIndex: Int = 0,
        val lessons: Map<Int, List<ScheduleInfo>> = emptyMap()

    ): ViewState

    sealed class Effect: ViewSideEffect {
        sealed class Navigation : Effect() {
            data object ToPresence : Navigation()
            data object ToBack: Navigation()
        }
        data class ShowError(val message: String?) : Effect()
    }
}
