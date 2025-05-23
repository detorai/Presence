package org.example.presenceapp.domain.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.example.presenceapp.domain.command.GroupCommand
import org.example.presenceapp.domain.entities.Either
import org.example.presenceapp.domain.entities.Presence
import org.example.presenceapp.domain.entities.Schedule
import org.example.presenceapp.domain.entities.ScheduleInfo
import org.example.presenceapp.domain.repo.GroupRepository

class ScheduleUseCase(
    private val groupRepository: GroupRepository
) {
    fun getSchedule(groupCommand: GroupCommand): Flow<Either<Exception, Map<Int, List<ScheduleInfo>>>> = flow {
        return@flow try {
            val remote = groupRepository.getSchedule(groupCommand)
            val grouped = groupRepository.getSchedule(groupCommand)
                .groupBy(
                    { it.dayOfWeek },
                    { it.scheduleInfo }
                ).mapValues { entry ->
                    entry.value.flatten()
                }
            groupRepository.saveSchedule(remote)
            emit(Either.Right(grouped))
        } catch (e: Exception) {
            emit(Either.Left(e))
        }
    }
    fun getLocalSchedule(): Flow<Either<Exception, Map<Int, List<ScheduleInfo>>>> = flow {
        return@flow try {
            val result = groupRepository.getLocalSchedule()
                .groupBy(
                    { it.dayOfWeek },
                    { it.scheduleInfo }
                ).mapValues { entry ->
                    entry.value.flatten()
                }
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