package org.example.presenceapp.domain.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.example.presenceapp.domain.command.GroupCommand
import org.example.presenceapp.domain.command.PresenceCommand
import org.example.presenceapp.domain.entities.AttendanceTypeNet
import org.example.presenceapp.domain.entities.Either
import org.example.presenceapp.domain.entities.Presence
import org.example.presenceapp.domain.entities.Presetting
import org.example.presenceapp.domain.repo.PresenceRepository

class PresenceUseCase(
    private val presenceRepository: PresenceRepository
) {
    fun getAttendanceType(): Flow<Either<Exception,List<AttendanceTypeNet>>> = flow {
        return@flow try {
            val result = presenceRepository.getAttendanceType()
            emit(Either.Right(result))
        } catch (e: Exception) {
            emit(Either.Left(e))
        }
    }
    fun getPresetting(groupCommand: GroupCommand): Flow<Either<Exception, List<Presetting>>> = flow {
        return@flow try {
            val result = presenceRepository.getPresetting(groupCommand)
            emit (Either.Right(result))
        } catch (e: Exception) {
            emit (Either.Left(e))
        }
    }
    fun sendPresence(presenceCommand: List<PresenceCommand>): Flow<Either<Exception, List<Presence>>> = flow {
        return@flow try {
            val result = presenceRepository.sendPresence(presenceCommand)
            emit (Either.Right(result))
        } catch (e: Exception) {
            emit(Either.Left(e))
        }
    }
}