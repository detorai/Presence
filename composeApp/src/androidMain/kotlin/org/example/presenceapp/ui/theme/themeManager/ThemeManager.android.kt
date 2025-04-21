package org.example.presenceapp.ui.theme.themeManager

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate

class AndroidThemeManager(
    private val context: Context
) : ThemeManager {
    private val prefs = context.getSharedPreferences("theme_prefs", Context.MODE_PRIVATE)

    override fun getCurrentTheme(): ThemeTypes {
        return try {
            ThemeTypes.valueOf(
                prefs.getString("app_theme", ThemeTypes.SYSTEM.name) ?: ThemeTypes.SYSTEM.name
            )
        } catch (e: Exception) {
            ThemeTypes.SYSTEM
        }
    }

    override fun setTheme(theme: ThemeTypes) {
        prefs.edit().putString("app_theme", theme.name).apply()
        applyThemeImmediately(context, theme)
    }

    private fun applyThemeImmediately(context: Context, theme: ThemeTypes) {
        val mode = when (theme) {
            ThemeTypes.LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
            ThemeTypes.DARK -> AppCompatDelegate.MODE_NIGHT_YES
            ThemeTypes.SYSTEM -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        }
        AppCompatDelegate.setDefaultNightMode(mode)
    }
}