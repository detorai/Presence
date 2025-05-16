package org.example.presenceapp.ui.navigation

import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.plus
import org.example.presenceapp.di.LocalAppNavigator
import org.example.presenceapp.ui.feature.login.composables.Login
import org.example.presenceapp.ui.feature.schedule.ScheduleContract
import org.example.presenceapp.ui.feature.schedule.ScheduleScreenModel
import org.example.presenceapp.ui.feature.schedule.composables.Schedule
import org.example.presenceapp.ui.feature.weeks.components.Weeks
import org.example.project.domain.models.Week
import org.example.project.ui.login.LoginContract
import org.example.project.ui.login.LoginViewModel
import org.example.project.ui.weeks.WeekContract
import org.example.project.ui.weeks.WeeksViewModel
import org.koin.compose.getKoin
import org.koin.core.parameter.parametersOf

sealed class Screens(): Screen {
    data object LoginScreen : Screens() {
        @Composable
        override fun Content() {
            val viewModel: LoginViewModel = koinScreenModel()
            val state = viewModel.viewState.collectAsState().value
            val snackbarHostState = remember { SnackbarHostState() }
            val navigator = LocalAppNavigator.current
            Login(
                viewModel,
                state,
                viewModel.effect,
                snackbarHostState,
                onNavigationRequested = {navigation ->
                    if (navigation is LoginContract.Effect.Navigation.ToHome) {
                        navigator.executeCommand(NavigationCommand.NavigateToWeeks)
                    }
                }
            )
        }
    }
    data object WeekScreen: Screens(){
        @Composable
        override fun Content() {
            val navigator = LocalAppNavigator.current
            val viewModel = rememberScreenModel { WeeksViewModel() }
            val state = viewModel.viewState.collectAsState().value
            Weeks(
                state,
                viewModel.effect,
                onNavigationRequested = {navigation ->
                    if (navigation is WeekContract.Effect.Navigation.ToSchedule) {
                        navigator.executeCommand(NavigationCommand.NavigateToSchedule(navigation.week))
                    }
                },
                onEventSent = { event ->
                    viewModel.setEvent(event)
                }
            )
        }
    }
    data class ScheduleScreen(private val week: Week): Screens() {
        @Composable
        override fun Content() {
            val viewModel = koinScreenModel<ScheduleScreenModel> { parametersOf(week)}
            val state = viewModel.viewState.collectAsState().value
            val effect = viewModel.effect
            val daysOfWeek = mapOf(
                "Пн" to week.startDate,
                "Вт" to week.startDate.plus(1, DateTimeUnit.DAY),
                "Ср" to week.startDate.plus(2, DateTimeUnit.DAY),
                "Чт" to week.startDate.plus(3, DateTimeUnit.DAY),
                "Пт" to week.startDate.plus(4, DateTimeUnit.DAY),
                "Сб" to week.startDate.plus(5, DateTimeUnit.DAY)
            )


            Schedule(
                state,
                daysOfWeek,
                effect,
                onEventSent = { event ->
                    viewModel.setEvent(event)
                }
            )
        }
    }
}