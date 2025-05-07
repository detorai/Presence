package org.example.presenceapp.ui.feature.settings

import androidx.compose.runtime.Composable
import org.example.presenceapp.PlatformContext

actual class SettingsManager actual constructor(platformContext: PlatformContext) {
    actual fun isDialogShown(): Boolean {
        TODO("Not yet implemented")
    }

    actual fun setDialogShown() {
    }
}

@Composable
actual fun getSettingsManager(): SettingsManager {
    TODO("Not yet implemented")
}