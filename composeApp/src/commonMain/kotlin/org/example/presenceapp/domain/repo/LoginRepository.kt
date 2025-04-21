package org.example.presenceapp.domain.repo

import org.example.presenceapp.domain.command.LoginCommand
import org.example.presenceapp.domain.entities.LoginResponse

interface LoginRepository {
    suspend fun login(loginCommand: LoginCommand): LoginResponse
}