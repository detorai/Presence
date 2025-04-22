package org.example.presenceapp.data.local.storage.login

interface UserInfoStorage {
    suspend fun setUserName(userName: String)
    suspend fun setUserGroup(userGroup: String)
    suspend fun setUserRole(userRole: String)

    suspend fun getUserName(): String?
    suspend fun getUserGroup(): String?
    suspend fun getUserRole(): String?
}