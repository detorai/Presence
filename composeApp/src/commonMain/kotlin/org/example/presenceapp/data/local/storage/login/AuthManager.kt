package org.example.project.data.local

class AuthManager(private val tokenStorage: TokenStorage) {
    suspend fun setToken(token: String) = tokenStorage.setToken(token)
    suspend fun deleteToken() = tokenStorage.deleteToken()
    suspend fun getToken(): String? = tokenStorage.getToken()
}