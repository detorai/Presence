package org.example.presenceapp.domain.repo

import org.example.presenceapp.domain.command.LoginCommand
import org.example.presenceapp.domain.entities.LoginResponse
import org.example.presenceapp.domain.entities.UserInfo

interface LoginRepository {
    suspend fun login(loginCommand: LoginCommand): LoginResponse
    suspend fun setUserInfo(loginResponse: LoginResponse)
    suspend fun getUserInfo(): UserInfo
}