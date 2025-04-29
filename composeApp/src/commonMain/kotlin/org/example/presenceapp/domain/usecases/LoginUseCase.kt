package org.example.presenceapp.domain.usecases

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import org.example.presenceapp.domain.entities.Either
import org.example.presenceapp.domain.command.LoginCommand
import org.example.presenceapp.domain.entities.LoginResponse
import org.example.presenceapp.domain.entities.UserInfo
import org.example.presenceapp.domain.repo.LoginRepository

class LoginUseCase(private val loginRepository: LoginRepository){
    fun login(loginCommand: LoginCommand): Flow<Either<Throwable, LoginResponse>> = flow<Either<Throwable, LoginResponse>> {
        val result = loginRepository.login(loginCommand)
            loginRepository.setUserInfo(result)
            emit(Either.Right(result))
    }
        .catch {emit(Either.Left(it))}
        .flowOn(Dispatchers.IO)
//        return@flow try {
//            val result = loginRepository.login(loginCommand)
//            loginRepository.setUserInfo(result)
//            emit(Either.Right(result))
//        } catch (e:Exception) {
//            emit(Either.Left(e))
//        }

    fun getUserInfo(): Flow<Either<Exception, UserInfo>> = flow {
        return@flow try {
            val result = loginRepository.getUserInfo()
            emit(Either.Right(result))
        } catch (e: Exception) {
            emit(Either.Left(e))
        }
    }
}