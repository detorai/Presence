package org.example.presenceapp.data.local.storage.login

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class AndroidUserInfoStorage(private val context: Context): UserInfoStorage {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_store")
    private val user_name = stringPreferencesKey("user_name")
    private val user_group = stringPreferencesKey("user_group")
    private val user_role = stringPreferencesKey("user_role")



    override suspend fun setUserName(userName: String) {
        context.dataStore.edit { preferences ->
            preferences[user_name] = userName
        }
    }

    override suspend fun setUserGroup(userGroup: String) {
        context.dataStore.edit { preferences ->
            preferences[user_group] = userGroup
        }
    }

    override suspend fun setUserRole(userRole: String) {
        context.dataStore.edit { preferences ->
            preferences[user_role] = userRole
        }
    }

    override suspend fun getUserName(): String? {
        return context.dataStore.data
            .map { preferences -> preferences[user_name] }
            .first()
    }

    override suspend fun getUserGroup(): String? {
        return context.dataStore.data
            .map { preferences -> preferences[user_group] }
            .first()
    }

    override suspend fun getUserRole(): String? {
        return context.dataStore.data
            .map { preferences -> preferences[user_role] }
            .first()
    }
}