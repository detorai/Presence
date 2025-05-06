package org.example.project.ui.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.example.presenceapp.ui.feature.login.composables.Login


class LoginScreen: Screen {

    @Composable
    override fun Content() {
        val navigator  = LocalNavigator.currentOrThrow
        val viewModel: LoginViewModel = koinScreenModel()
        val state = viewModel.viewState.collectAsState().value

        Login(
            viewModel,
            state,
            viewModel.effect
            )
    }
}