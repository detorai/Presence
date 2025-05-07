package org.example.presenceapp.ui.feature.login.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.Flow
import org.example.presenceapp.ui.base.SIDE_EFFECTS_KEY
import org.example.project.ui.login.LoginContract
import org.example.project.ui.login.LoginViewModel

@Composable
fun Login(
    viewModel: LoginViewModel,
    state: LoginContract.State,
    effectFlow: Flow<LoginContract.Effect>,
    snackbarState: SnackbarHostState
) {

    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effectFlow.collect { effect ->
            when (effect) {
                is LoginContract.Effect.Navigation.ToHome ->
                {}
                is LoginContract.Effect.ShowError -> {
                    snackbarState.showSnackbar(
                        message = effect.message!!,
                        duration = SnackbarDuration.Short
                    )
                }
            }
        }
    }

    Scaffold(
        topBar = {
            LoginTopBar(30)
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarState
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                PresenceTextField(
                    value = state.login,
                    onValue = { viewModel.setEvent(LoginContract.Event.LoginChanged(it)) },
                    placeholder = "Логин",
                    text = "Логин",
                    top = 145
                )

                PresenceTextField(
                    value = state.password,
                    onValue = { viewModel.setEvent(LoginContract.Event.PasswordChanged(it)) },
                    placeholder = "Пароль",
                    text = "Пароль",
                    top = 18,
                )

                CheckBoxRow(
                    check = state.check,
                    onCheck = { viewModel.setEvent(LoginContract.Event.ToggleCheck) },
                    top = 24
                )
            }

            PresenceButton(
                text = "Войти",
                onClick = { viewModel.setEvent(LoginContract.Event.SubmitLogin) },
                bottom = 80,
            )
        }
    }
}