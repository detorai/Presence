package org.example.presenceapp.ui.theme

import androidx.compose.runtime.Composable
import org.example.presenceapp.ui.theme.themeManager.IosThemeManager
import org.example.presenceapp.ui.theme.themeManager.ThemeObserver
import org.example.presenceapp.ui.theme.themeManager.ThemeState

@Composable
actual fun rememberThemeState(): ThemeState {
    return ThemeState(
        themeManager = IosThemeManager(),
        isSystemDark = ThemeObserver.isDarkMode()
    )
}