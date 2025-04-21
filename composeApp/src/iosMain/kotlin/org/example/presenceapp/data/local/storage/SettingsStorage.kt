package org.example.presenceapp.data.local.storage

import org.example.presenceapp.PlatformContext
import platform.Foundation.NSUserDefaults

actual class SettingsStorage actual constructor(private val context: PlatformContext) {
    private val userDefaults = NSUserDefaults.standardUserDefaults()

    actual suspend fun put(key: String, value: String) {
        userDefaults.setObject(value, forKey = key)
    }

    actual suspend fun get(key: String, defaultValue: String): String {
        return userDefaults.stringForKey(key) ?: defaultValue
    }
}