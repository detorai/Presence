package org.example.presenceapp.ui.feature.settings

import androidx.compose.runtime.Composable
import org.example.presenceapp.PlatformContext

expect class SettingsManager(platformContext: PlatformContext) {
    fun isDialogShown(): Boolean
    fun setDialogShown()
}

@Composable
expect fun getSettingsManager(): SettingsManager