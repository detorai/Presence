package org.example.presenceapp.ui.settings

import androidx.compose.runtime.Composable
import org.example.presenceapp.PlatformContext
import platform.Foundation.NSUserDefaults

actual class SettingsManager actual constructor(platformContext: PlatformContext) {
    private val userDefaults = NSUserDefaults.standardUserDefaults()

    actual fun isDialogShown(): Boolean {
        return userDefaults.boolForKey(KEY_DIALOG_SHOWN) ?: false
    }

    actual fun setDialogShown() {
        userDefaults.setBool(true, forKey = KEY_DIALOG_SHOWN)
    }

    companion object {
        private const val KEY_DIALOG_SHOWN = "dialog_shown"
    }
}

@Composable
actual fun getSettingsManager(): SettingsManager {
    val platformContext = PlatformContext()
    return SettingsManager(platformContext)
}