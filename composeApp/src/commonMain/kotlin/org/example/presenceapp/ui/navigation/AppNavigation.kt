package org.example.presenceapp.ui.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.example.presenceapp.ui.feature.login.composables.Login
import org.example.project.ui.login.LoginViewModel

sealed class AppNavigation: Screen {
    fun navigateTo(navigator: Navigator, screen: Screen){

    }
    data object LoginScreen: AppNavigation() {
        @Composable
        override fun Content() {
            val viewModel: LoginViewModel = koinScreenModel()
            val state = viewModel.viewState.collectAsState().value
            val snackbarHostState = remember { SnackbarHostState() }

            Login(
                viewModel,
                state,
                viewModel.effect,
                snackbarHostState
            )
        }
    }
    data object WeekScreen: AppNavigation() {
        @Composable
        override fun Content() {
            TODO("Not yet implemented")
        }
    }
}