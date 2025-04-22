package org.example.project.data.local

import org.example.presenceapp.data.local.storage.login.UserInfoStorage

class AuthManager(private val tokenStorage: TokenStorage, private val userInfoStorage: UserInfoStorage) {
    suspend fun setToken(token: String) = tokenStorage.setToken(token)
    suspend fun deleteToken() = tokenStorage.deleteToken()
    suspend fun getToken(): String? = tokenStorage.getToken()

    suspend fun setUserName(userName: String) = userInfoStorage.setUserName(userName)
    suspend fun setUserGroup(userGroup: String) = userInfoStorage.setUserGroup(userGroup)
    suspend fun setUserRole(userRole: String) = userInfoStorage.setUserRole(userRole)

    suspend fun getUserName() = userInfoStorage.getUserName()
    suspend fun getUserGroup() = userInfoStorage.getUserGroup()
    suspend fun getUserRole() = userInfoStorage.getUserRole()
}