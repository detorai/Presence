package org.example.presenceapp.domain.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.example.presenceapp.domain.entities.Either
import org.example.presenceapp.domain.entities.LoginRequest
import org.example.presenceapp.domain.entities.LoginResponse
import org.example.presenceapp.domain.repo.LoginRepository

class LoginUseCase(private val loginRepository: LoginRepository){
    fun login(loginRequest: LoginRequest): Flow<Either<Exception, LoginResponse>> = flow {
        return@flow try {
            val result = loginRepository.login(loginRequest)
            emit(Either.Right(result))
        } catch (e:Exception) {
            emit(Either.Left(e))
        }
    }
}