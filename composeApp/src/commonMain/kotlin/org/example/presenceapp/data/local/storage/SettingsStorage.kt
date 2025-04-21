package org.example.presenceapp.data.local.storage

import org.example.presenceapp.PlatformContext

expect class SettingsStorage(context: PlatformContext) {
    suspend fun put(key: String, value: String)
    suspend fun get(key: String, defaultValue: String): String
}