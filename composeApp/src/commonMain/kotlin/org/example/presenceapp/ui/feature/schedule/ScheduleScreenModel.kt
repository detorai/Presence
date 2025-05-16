package org.example.presenceapp.ui.feature.schedule

import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.launch
import org.example.presenceapp.domain.entities.Either
import org.example.presenceapp.domain.usecases.ScheduleUseCase
import org.example.presenceapp.ui.base.BaseViewModel
import org.example.project.domain.models.Week

class ScheduleScreenModel(
    private val scheduleUseCase: ScheduleUseCase,
    private val week: Week
):  BaseViewModel<ScheduleContract.Event, ScheduleContract.State, ScheduleContract.Effect>() {


    init {
        getAllSchedule()
        setState { copy(weeks = week) }
    }

    override fun setInitialState() = ScheduleContract.State()

    override fun handleEvents(event: ScheduleContract.Event) {
        when (event) {
            is ScheduleContract.Event.UpdateSelectedDay -> {
                setState { copy(currentDayIndex = event.index) }
                setEffect { ScheduleContract.Effect.SelectedPageChanged(event.index) }
            }
            is ScheduleContract.Event.SelectLesson -> {
                setEffect { ScheduleContract.Effect.Navigation.ToPresence(event.lesson) }
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