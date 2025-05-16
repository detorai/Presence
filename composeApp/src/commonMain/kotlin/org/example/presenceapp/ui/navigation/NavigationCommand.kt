package org.example.presenceapp.ui.navigation

import org.example.project.domain.models.Week

sealed class NavigationCommand {
    data object PopBack : NavigationCommand()
    data object NavigateToWeeks :  NavigationCommand()
    data class NavigateToSchedule(val week: Week) : NavigationCommand()
}