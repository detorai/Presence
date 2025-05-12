package org.example.project.ui.login

import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.launch
import org.example.presenceapp.domain.command.GroupCommand
import org.example.presenceapp.domain.command.LoginCommand
import org.example.presenceapp.domain.entities.Either
import org.example.presenceapp.domain.usecases.LoginUseCase
import org.example.presenceapp.domain.usecases.ScheduleUseCase
import org.example.presenceapp.ui.base.BaseViewModel


class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val scheduleUseCase: ScheduleUseCase
) : BaseViewModel<LoginContract.Event, LoginContract.State, LoginContract.Effect>() {

    override fun setInitialState() = LoginContract.State()

    override fun handleEvents(event: LoginContract.Event) {
        when (event) {
            is LoginContract.Event.LoginChanged -> {
                setState { copy(login = event.login) }
            }
            is LoginContract.Event.PasswordChanged -> {
                setState { copy(password = event.password) }
            }
            LoginContract.Event.ToggleCheck -> {
                setState { copy(check = !check) }
            }
            LoginContract.Event.SubmitLogin -> {
                val login = viewState.value.login
                val password = viewState.value.password
                login(login, password)
            }
            LoginContract.Event.DismissError -> {
                setState { copy(error = null) }
            }
        }
    }

    private fun login(login: String, password: String) {
        screenModelScope.launch {
            loginUseCase.login(LoginCommand(login, password))
                .collect { response ->
                    when (response) {
                        is Either.Right -> {
                            val groupId = response.value.user.responsible.first().group.id
                            getSchedule(groupId)
                            
                            getPresence(groupId)
                        }
                        is Either.Left -> {
                            setEffect { LoginContract.Effect.ShowError(response.value.message) }
                        }
                    }
                }
        }
    }

    private suspend fun getSchedule(groupId: Int) {
        val result = scheduleUseCase.getSchedule(GroupCommand(groupId))
        result.collect { scheduleResponse ->
            when (scheduleResponse) {
                is Either.Right -> {
                    setEffect { LoginContract.Effect.Navigation.ToHome }
                }
                is Either.Left -> {
                    setEffect { LoginContract.Effect.ShowError(scheduleResponse.value.message) }
                }
            }
        }
    }
    private suspend fun getPresence(groupId: Int) {
        val result = scheduleUseCase.getPresence(GroupCommand(groupId))
        result.collect { presenceResponse ->
            when (presenceResponse) {
                is Either.Right -> {
                    setEffect { LoginContract.Effect.Navigation.ToHome }
                }
                is Either.Left -> {
                    setEffect { LoginContract.Effect.ShowError(presenceResponse.value.message) }
                }
            }
        }
    }
}