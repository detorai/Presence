package org.example.presenceapp.ui.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import org.example.presenceapp.ui.feature.login.composables.Login
import org.example.presenceapp.ui.feature.schedule.ScheduleScreen
import org.example.presenceapp.ui.feature.weeks.components.Weeks
import org.example.project.ui.login.LoginContract
import org.example.project.ui.login.LoginViewModel
import org.example.project.ui.weeks.WeekContract
import org.example.project.ui.weeks.WeeksViewModel
import org.koin.compose.getKoin

sealed class Screens(): Screen {
    data object LoginScreen : Screens() {
        @Composable
        override fun Content() {
            val viewModel: LoginViewModel = koinScreenModel()
            val state = viewModel.viewState.collectAsState().value
            val snackbarHostState = remember { SnackbarHostState() }
            val navigator = getKoin().get<GlobalNavigator>()
            Login(
                viewModel,
                state,
                viewModel.effect,
                snackbarHostState,
                onNavigationRequested = {navigation ->
                    if (navigation is LoginContract.Effect.Navigation.ToHome) {
                        navigator.push(WeekScreen)
                    }
                }
            )
        }
    }
    data object WeekScreen: Screens(){
        @Composable
        override fun Content() {
            val navigator = getKoin().get<GlobalNavigator>()
            val viewModel = rememberScreenModel { WeeksViewModel() }
            val state = viewModel.viewState.collectAsState().value
            val snackbarHostState = remember { SnackbarHostState() }
            Weeks(
                viewModel,
                state,
                viewModel.effect,
                snackbarHostState,
                onNavigationRequested = {navigation ->
                    if (navigation is WeekContract.Effect.Navigation.ToSchedule) {
                        navigator.push(ScheduleScreen(navigation.week))
                    }
                }
            )
        }
    }
}