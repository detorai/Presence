package org.example.presenceapp.data.local.storage.login

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import org.example.project.data.local.TokenStorage

class AndroidTokenStorage(private val context: Context): TokenStorage {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth_store")
    private val tokenKey = stringPreferencesKey("auth_token")

    override suspend fun getToken(): String? {
        return context.dataStore.data
            .map { preferences -> preferences[tokenKey] }
            .first()
    }
    override suspend fun setToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[tokenKey] = token
        }
    }
    override suspend fun deleteToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(tokenKey)
        }
    }
}