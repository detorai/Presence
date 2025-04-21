package org.example.presenceapp.ui.settings

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.core.content.edit
import org.example.presenceapp.PlatformContext
import org.example.presenceapp.getPlatformContext

actual class SettingsManager actual constructor(platformContext: PlatformContext) {
    private val sharedPreferences: SharedPreferences =
        platformContext.context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)

    actual fun isDialogShown(): Boolean {
        return sharedPreferences.getBoolean(KEY_DIALOG_SHOWN, false)
    }

    actual fun setDialogShown() {
        sharedPreferences.edit() { putBoolean(KEY_DIALOG_SHOWN, true) }
    }

    companion object {
        private const val KEY_DIALOG_SHOWN = "dialog_shown"
    }
}

@Composable
actual fun getSettingsManager(): SettingsManager {
    val platformContext = getPlatformContext()
    return SettingsManager(platformContext)
}