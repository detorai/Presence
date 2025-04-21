package org.example.presenceapp

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

actual class PlatformContext(val context: Context)

@Composable
actual fun getPlatformContext(): PlatformContext {
    return PlatformContext(LocalContext.current)
}