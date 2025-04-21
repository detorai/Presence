package org.example.presenceapp.domain.repo

import org.example.presenceapp.domain.entities.LoginRequest
import org.example.presenceapp.domain.entities.LoginResponse

interface LoginRepository {
    suspend fun login(loginRequest: LoginRequest): LoginResponse
}