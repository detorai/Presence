package org.example.presenceapp.data.local.storage

import android.content.Context
import android.content.SharedPreferences
import org.example.presenceapp.PlatformContext

actual class SettingsStorage actual constructor(private val context: PlatformContext) {
    private val sharedPreferences: SharedPreferences by lazy {
        context.context.getSharedPreferences("app_settings", Context.MODE_PRIVATE)
    }

    actual suspend fun put(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    actual suspend fun get(key: String, defaultValue: String): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }
}