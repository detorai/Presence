package org.example.presenceapp.domain.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.example.presenceapp.domain.entities.Either
import org.example.presenceapp.domain.entities.Schedule
import org.example.presenceapp.domain.repo.ScheduleRepository

class ScheduleUseCase(
    private val scheduleRepository: ScheduleRepository
) {
    fun getSchedule(group_id: Int): Flow<Either<Exception, List<Schedule>>> = flow {
        return@flow try {
            val result = scheduleRepository.getSchedule(group_id)
            emit(Either.Right(result))
        } catch (e: Exception) {
            emit(Either.Left(e))
        }
    }
}