package org.example.presenceapp.data.repository

import org.example.presenceapp.data.common.toDto
import org.example.presenceapp.data.common.toEntity
import org.example.presenceapp.data.local.sql.cache.Database
import org.example.presenceapp.data.local.sql.cache.DatabaseDriverFactory
import org.example.presenceapp.data.remote.impl.AuthApiImpl
import org.example.presenceapp.domain.command.LoginCommand
import org.example.presenceapp.domain.entities.LoginResponse
import org.example.presenceapp.domain.entities.UserInfo
import org.example.presenceapp.domain.repo.LoginRepository
import org.example.project.data.local.AuthManager

class AuthRepository(
    databaseDriverFactory: DatabaseDriverFactory,
    private val authApiImpl: AuthApiImpl,
    private val authManager: AuthManager,
): LoginRepository {
    private val database = Database(databaseDriverFactory)

    override suspend fun login(loginCommand: LoginCommand): LoginResponse {
        val result = authApiImpl.login(loginCommand.toDto())
        authManager.setToken(result.token)
        return result.toEntity()
    }

    override suspend fun setUserInfo(loginResponse: LoginResponse) {
        authManager.setUserName(loginResponse.user.fio)
        authManager.setUserGroup(loginResponse.user.responsible.first().group.name)
        authManager.setUserRole(loginResponse.user.responsible.first().responsibleType.name)
    }

    override suspend fun getUserInfo(): UserInfo {
        val userName = authManager.getUserName()
        val userGroup = authManager.getUserGroup()
        val userRole = authManager.getUserRole()
        return UserInfo(userName!!, userGroup!!, userRole!!)
    }
}