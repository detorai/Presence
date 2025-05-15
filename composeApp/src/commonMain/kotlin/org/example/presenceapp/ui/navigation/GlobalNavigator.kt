package org.example.presenceapp.ui.navigation

import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator

class GlobalNavigator(private val navigator: Navigator): AppNavigator {
    override fun push(screen: Screen) {
        require(screen is Screens)
        navigator.push(screen)
    }

    override fun pop() {
        navigator.pop()
    }

}