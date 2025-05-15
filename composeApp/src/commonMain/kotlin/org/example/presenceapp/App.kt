package org.example.presenceapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import org.example.presenceapp.di.LocalAppNavigator
import org.example.presenceapp.ui.navigation.GlobalNavigator
import org.example.presenceapp.ui.navigation.Screens
import org.example.presenceapp.ui.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    AppTheme {
        Navigator(Screens.LoginScreen){navigator ->
            CompositionLocalProvider(
                LocalAppNavigator provides GlobalNavigator(navigator)
            ){
                CurrentScreen()
            }
        }
    }
}