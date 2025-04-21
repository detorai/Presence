package org.example.presenceapp.data.repository

import org.example.presenceapp.data.common.toDto
import org.example.presenceapp.data.common.toEntity
import org.example.presenceapp.data.remote.impl.AuthApiImpl
import org.example.presenceapp.domain.entities.LoginRequest
import org.example.presenceapp.domain.entities.LoginResponse
import org.example.presenceapp.domain.repo.LoginRepository
import org.example.project.data.local.AuthManager

class AuthRepository(
    private val authApiImpl: AuthApiImpl,
    private val authManager: AuthManager,
): LoginRepository {
    override suspend fun login(loginRequest: LoginRequest): LoginResponse {
        val result = authApiImpl.login(loginRequest.toDto())
        authManager.setToken(result.token)
        return result.toEntity()
    }
}