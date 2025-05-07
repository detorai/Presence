package org.example.presenceapp.ui.feature.schedule

import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.snapshotFlow
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.presenceapp.domain.entities.Either
import org.example.presenceapp.domain.entities.Schedule
import org.example.presenceapp.domain.repo.GroupRepository
import org.example.presenceapp.domain.usecases.ScheduleUseCase
import org.example.presenceapp.ui.base.BaseViewModel

class ScheduleScreenModel(
    private val scheduleUseCase: ScheduleUseCase
):  BaseViewModel<ScheduleContract.Event, ScheduleContract.State, ScheduleContract.Effect>() {

    init {
        getAllSchedule()
    }

    override fun setInitialState() = ScheduleContract.State()

    fun handlePagerState(pagerState: PagerState) {
        screenModelScope.launch {
            snapshotFlow { pagerState.isScrollInProgress }
                .collect { isScrolling ->
                    setEvent(ScheduleContract.Event.UpdateScrollState(isScrolling))

                    if (!isScrolling) {
                        setEvent(ScheduleContract.Event.UpdateSelectedDay(
                            index = pagerState.currentPage,
                            isManual = false
                        ))
                    }
                }
        }

        screenModelScope.launch {
            viewState.collect{ currentState ->
                if (!pagerState.isScrollInProgress) {
                    pagerState.animateScrollToPage(currentState.currentDayIndex)
                }
            }
        }
    }


    override fun handleEvents(event: ScheduleContract.Event) {
        val state = viewState.value
        when (event) {
            is ScheduleContract.Event.UpdateScrollState -> {
                setState { copy(isUserSwiping = event.isScrolling) }
            }
            is ScheduleContract.Event.UpdateSelectedDay -> {
                if (!state.isUserSwiping || event.isManual) {
                    setState { copy(currentDayIndex = event.index) }
                }
            }
            is ScheduleContract.Event.SelectDay -> {
                if (!state.isUserSwiping) {
                    setState { copy(currentDayIndex = event.index) }
                }
            }
            is ScheduleContract.Event.SelectLesson -> {
                setState { copy(schedule = event.lesson) }
            }
            is ScheduleContract.Event.SetSwipeState -> {
                setState { copy(isUserSwiping = event.swiping) }
            }
            ScheduleContract.Event.SubmitSchedule -> {

            }
        }
    }
    private fun getAllSchedule(){
        screenModelScope.launch {
            val result = scheduleUseCase.getLocalSchedule()
            result.collect {res ->
                when (res) {
                    is Either.Right -> {
                        setState {
                            copy (
                                success = true,
                                lessons = res.value
                            )
                        }
                    }
                    is Either.Left -> {
                        setState { copy(error = res.value.message) }
                    }
                }
            }
        }
    }
}