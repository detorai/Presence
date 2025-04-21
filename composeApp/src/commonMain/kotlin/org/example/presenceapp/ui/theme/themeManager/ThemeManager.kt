package org.example.presenceapp.ui.theme.themeManager

interface ThemeManager {
    fun getCurrentTheme(): ThemeTypes
    fun setTheme(theme: ThemeTypes)
}