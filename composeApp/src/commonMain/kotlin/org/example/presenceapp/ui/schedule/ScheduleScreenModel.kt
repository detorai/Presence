package org.example.presenceapp.ui.schedule

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.presenceapp.domain.entities.Schedule
import org.example.project.domain.models.Week

data class ScheduleScreenModel(
    private val lessons: List<Schedule>,
    private val week: Week

): ScreenModel {
    val state = MutableStateFlow(ScheduleScreenState())

    private val _currentDayIndex = MutableStateFlow(0)
    val currentDayIndex: StateFlow<Int> = _currentDayIndex.asStateFlow()

    init {
        getGroup(lessons)
    }

    fun setSwipeState(swiping: Boolean) {
        state.update {
            it.copy(
                isUserSwiping = swiping
            )
        }
    }

    fun selectDay(index: Int) {
        if (!state.value.isUserSwiping) {
            _currentDayIndex.value = index
        }
    }

    fun selectLesson(lesson: Schedule) {
        state.update {
            it.copy(
                schedule = lesson
            )
        }
    }
    fun getGroup(lesson: List<Schedule>){
        screenModelScope.launch {
            state.update {
                it.copy(
                    lessonsList = lesson
                )
            }
        }
    }
}