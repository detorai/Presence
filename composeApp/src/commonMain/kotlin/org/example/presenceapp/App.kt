package org.example.presenceapp

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import org.example.presenceapp.ui.schedule.ScheduleScreen
import org.example.presenceapp.ui.theme.AppTheme
import org.example.project.ui.login.LoginScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    AppTheme {
        Navigator(LoginScreen())
    }
}