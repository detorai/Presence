package org.example.presenceapp.ui.theme

import android.content.ComponentCallbacks
import android.content.Context
import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import org.example.presenceapp.ui.theme.themeManager.AndroidThemeManager
import org.example.presenceapp.ui.theme.themeManager.ThemeState

@Composable
actual fun rememberThemeState(): ThemeState {
    val context = LocalContext.current
    val configuration = remember { mutableStateOf(context.resources.configuration) }

    DisposableEffect(Unit) {
        val listener = object: ComponentCallbacks {
            override fun onConfigurationChanged(newConfig: Configuration) {
                configuration.value = newConfig
            }
            override fun onLowMemory() {}
        }

        context.registerComponentCallbacks(listener)
        onDispose {
            context.unregisterComponentCallbacks(listener)
        }
    }

    return remember {
        ThemeState(
            themeManager = AndroidThemeManager(context),
            isSystemDark = isSystemDark(context)
        )
    }
}

private fun isSystemDark(context: Context): Boolean {
    val configuration = context.resources.configuration
    return (configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES
}
