package org.example.presenceapp.ui.navigation

import cafe.adriel.voyager.core.screen.Screen

interface AppNavigator {
    fun push(screen: Screen)
    fun pop()
}