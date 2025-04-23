package org.example.presenceapp.domain.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.example.presenceapp.domain.command.GroupCommand
import org.example.presenceapp.domain.entities.Either
import org.example.presenceapp.domain.entities.Presence
import org.example.presenceapp.domain.entities.Schedule
import org.example.presenceapp.domain.entities.Subject
import org.example.presenceapp.domain.repo.ScheduleRepository

class ScheduleUseCase(
    private val scheduleRepository: ScheduleRepository
) {
    fun getSchedule(groupCommand: GroupCommand): Flow<Either<Exception, List<Schedule>>> = flow {
        return@flow try {
            val result = scheduleRepository.getSchedule(groupCommand)
            result.forEach { res ->
                scheduleRepository.setSubject(res.subject)
                scheduleRepository.setSchedule(res)
            }
            emit(Either.Right(result))
        } catch (e: Exception) {
            emit(Either.Left(e))
        }
    }
    fun getLocalSchedule(): Flow<Either<Exception, List<Schedule>>> = flow {
        return@flow try {
            val result = scheduleRepository.getLocalSchedule()
            emit(Either.Right(result))
        } catch (e: Exception) {
            emit(Either.Left(e))
        }
    }

    fun getPresence(groupCommand: GroupCommand): Flow<Either<Exception, List<Presence>>> = flow {
        return@flow try {
            val result = scheduleRepository.getPresenceByGroup(groupCommand)
            emit(Either.Right(result))
        } catch (e: Exception) {
            emit(Either.Left(e))
        }
    }
}