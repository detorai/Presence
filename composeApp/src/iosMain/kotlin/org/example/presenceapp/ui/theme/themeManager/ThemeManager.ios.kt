package org.example.presenceapp.ui.theme.themeManager

import platform.Foundation.setValue
import platform.UIKit.UIApplication
import platform.UIKit.UIUserInterfaceStyle
import platform.UIKit.UIWindow

@Suppress("CONFLICTING_OVERLOADS")
fun UIWindow.setOverrideUserInterfaceStyle(style: UIUserInterfaceStyle) {
    this.setValue(style, forKey = "overrideUserInterfaceStyle")
}

class IosThemeManager: ThemeManager {
    private var currentTheme: ThemeTypes = ThemeTypes.SYSTEM

    override fun getCurrentTheme() = currentTheme
    override fun setTheme(theme: ThemeTypes) {
        currentTheme = theme
        updateAllWindowsTheme(theme)
    }

    private fun updateAllWindowsTheme(theme: ThemeTypes) {
        val style = when (theme) {
            ThemeTypes.LIGHT -> UIUserInterfaceStyle.UIUserInterfaceStyleLight
            ThemeTypes.DARK -> UIUserInterfaceStyle.UIUserInterfaceStyleDark
            ThemeTypes.SYSTEM -> UIUserInterfaceStyle.UIUserInterfaceStyleUnspecified
        }

        (UIApplication.sharedApplication.windows as? List<UIWindow>)?.forEach { window ->
            window.setOverrideUserInterfaceStyle(style)
        }
    }
}

external class ThemeObserver: platform.darwin.NSObject {
    companion object {
        fun isDarkMode(): Boolean
    }
}