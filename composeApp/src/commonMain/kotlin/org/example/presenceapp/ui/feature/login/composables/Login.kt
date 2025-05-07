package org.example.presenceapp.ui.feature.login.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.Flow
import org.example.presenceapp.ui.base.SIDE_EFFECTS_KEY
import org.example.presenceapp.ui.theme.AppTheme
import org.example.project.ui.login.LoginContract
import org.example.project.ui.login.LoginViewModel




@Composable
fun Login(
    viewModel: LoginViewModel,
    state: LoginContract.State,
    effectFlow: Flow<LoginContract.Effect>?
){
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effectFlow?.collect { effect ->
            when (effect) {
                is LoginContract.Effect.Navigation.ToHome -> {

                }

                is LoginContract.Effect.ShowError -> {
                    snackbarHostState.showSnackbar(
                        message = effect.message!!,
                        duration = SnackbarDuration.Short
                    )
                }
            }
        }
    }
    Scaffold(
        topBar = {
            LoginTopBar()
        },
        snackbarHost = {
            SnackbarHost(
                snackbarHostState
            )
        }
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize().background(AppTheme.colors.white).padding(horizontal = 32.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(top = 142.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                PresenceTextField(
                    value = state.login,
                    onValue = { value ->
                        viewModel.setEvent(
                            LoginContract.Event.LoginChanged(value)
                        )
                    },
                    placeholder = "xyz",
                    text = "Логин",
                    top = 145

                )
                PresenceTextField(
                    value = state.password,
                    onValue = {
                        viewModel.setEvent(
                            LoginContract.Event.PasswordChanged(it)
                        )
                    },
                    placeholder = "********",
                    text = "Пароль",
                    top = 18
                )
                CheckBoxRow(
                    check = state.check,
                    onCheck = {
                        viewModel.setEvent(
                            LoginContract.Event.ToggleCheck
                        )
                    },
                    top = 24
                )
            }
            PresenceButton(
                text = "Войти",
                onClick = {
                    viewModel.setEvent(LoginContract.Event.SubmitLogin)
                },
                bottom = 80
            )
        }
    }
}