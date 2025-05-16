package org.example.presenceapp.ui.navigation

import cafe.adriel.voyager.core.screen.Screen
import kotlin.reflect.KClass

interface AppNavigator {
    fun executeCommand(command: NavigationCommand)
}