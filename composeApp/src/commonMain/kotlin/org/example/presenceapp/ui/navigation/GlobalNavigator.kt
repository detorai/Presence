package org.example.presenceapp.ui.navigation

import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import org.example.project.domain.models.Week
import kotlin.reflect.KClass

class GlobalNavigator(private val navigator: Navigator): AppNavigator {
    override fun executeCommand(command: NavigationCommand) {
        when (command) {
            is NavigationCommand.PopBack -> navigator.pop()
            is NavigationCommand.NavigateToWeeks -> navigator.push(Screens.WeekScreen)
            is NavigationCommand.NavigateToSchedule -> navigator.push(Screens.ScheduleScreen(command.week))
        }
    }
}