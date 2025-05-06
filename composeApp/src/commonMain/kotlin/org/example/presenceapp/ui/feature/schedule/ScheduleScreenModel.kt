package org.example.presenceapp.ui.feature.schedule

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

class ScheduleScreenModel(
    groupRepository: GroupRepository
): ScreenModel {
    val state = MutableStateFlow(ScheduleScreenState())
    private val scheduleUseCase = ScheduleUseCase(groupRepository)

    private val _currentDayIndex = MutableStateFlow(0)
    val currentDayIndex: StateFlow<Int> = _currentDayIndex.asStateFlow()

    init {
//        getAllSchedule()
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
    fun resetError(){
        state.update{
            it.copy(
                error = null
            )
        }
    }
//    private fun getAllSchedule(){
//        screenModelScope.launch {
//            val result = scheduleUseCase.getLocalSchedule()
//            result.collect{res ->
//                when (res) {
//                    is Either.Right -> {
//                        state.update {
//                            it.copy(
//                                lessonsList = res.value,
//                                success = true
//                            )
//                        }
//                    }
//                    is Either.Left -> {
//                        state.update {
//                            it.copy(
//                                error = res.value.message
//                            )
//                        }
//                    }
//                }
//            }
//        }
//    }
}