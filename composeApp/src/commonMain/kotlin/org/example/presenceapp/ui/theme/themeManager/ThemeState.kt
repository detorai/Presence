package org.example.presenceapp.ui.theme.themeManager

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class ThemeState(
    private val themeManager: ThemeManager,
    private val isSystemDark: Boolean
) {
    var currentTheme by mutableStateOf(themeManager.getCurrentTheme())
        private set

    val isDarkMode: Boolean
        @Composable get() = when(currentTheme) {
            ThemeTypes.LIGHT -> false
            ThemeTypes.DARK -> true
            ThemeTypes.SYSTEM -> isSystemDark
        }

    fun setTheme(theme: ThemeTypes) {
        themeManager.setTheme(theme)
        currentTheme = theme
    }
}