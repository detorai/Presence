package org.example.presenceapp.di

import androidx.compose.runtime.staticCompositionLocalOf
import org.example.presenceapp.ui.navigation.AppNavigator

val LocalAppNavigator = staticCompositionLocalOf<AppNavigator> {
    error("AppNavigator not provided. Did you forget to setup navigation?")
}