package org.example.presenceapp.domain.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.example.presenceapp.domain.command.GroupCommand
import org.example.presenceapp.domain.entities.Either
import org.example.presenceapp.domain.entities.Presence
import org.example.presenceapp.domain.entities.Schedule
import org.example.presenceapp.domain.repo.GroupRepository

class ScheduleUseCase(
    private val groupRepository: GroupRepository
) {
    fun getSchedule(groupCommand: GroupCommand): Flow<Either<Exception, List<Schedule>>> = flow {
        return@flow try {
            val result = groupRepository.getSchedule(groupCommand)
            result.forEach { res ->
                groupRepository.setSubject(res.subject)
                groupRepository.setSchedule(res)
            }
            emit(Either.Right(result))
        } catch (e: Exception) {
            emit(Either.Left(e))
        }
    }
    fun getLocalSchedule(): Flow<Either<Exception, List<Schedule>>> = flow {
        return@flow try {
            val result = groupRepository.getLocalSchedule()
            emit(Either.Right(result))
        } catch (e: Exception) {
            emit(Either.Left(e))
        }
    }

    fun getPresence(groupCommand: GroupCommand): Flow<Either<Exception, List<Presence>>> = flow {
        return@flow try {
            val result = groupRepository.getPresenceByGroup(groupCommand)
            emit(Either.Right(result))
        } catch (e: Exception) {
            emit(Either.Left(e))
        }
    }
}