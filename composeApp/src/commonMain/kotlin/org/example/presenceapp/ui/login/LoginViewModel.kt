package org.example.project.ui.login

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.presenceapp.domain.entities.Either
import org.example.presenceapp.domain.entities.LoginRequest
import org.example.presenceapp.domain.entities.UserInfo
import org.example.presenceapp.domain.repo.LoginRepository
import org.example.presenceapp.domain.repo.ScheduleRepository
import org.example.presenceapp.domain.usecases.LoginUseCase
import org.example.presenceapp.domain.usecases.ScheduleUseCase


class LoginViewModel(
    loginRepository: LoginRepository,
    scheduleRepository: ScheduleRepository
): ScreenModel {
    val state = MutableStateFlow(LoginScreenState())
    private val loginUseCase = LoginUseCase(loginRepository)
    private val scheduleUseCase = ScheduleUseCase(scheduleRepository)

    fun resetError(){
        state.update{
            it.copy(
                error = null
            )
        }
    }
    fun onLogin(login: String){
        state.update {
            it.copy(
                login = login
            )
        }
    }
    fun onPassword(password: String){
        state.update {
            it.copy(
                password = password
            )
        }
    }
    fun onCheck(){
        state.update {
            it.copy(
                check = !it.check
            )
        }
    }

    fun login(login: String, password: String) {
        val loginRequest = LoginRequest(login, password)
        screenModelScope.launch {
            val result = loginUseCase.login(loginRequest)
            result.collect{response ->
                when (response) {
                    is Either.Right -> {
                        val userResponse = response.value.user
                        val groupId = userResponse.responsible.first().group.id
                        UserInfo.userGroup = userResponse.responsible.first().group.name
                        UserInfo.userName = userResponse.fio
                        UserInfo.userRole = userResponse.role.name
                        getSchedule(groupId)
                    }
                    is Either.Left -> {
                        state.update {
                            it.copy(
                                error = response.value.message
                            )
                        }
                    }
                }
            }
        }
    }
    fun getSchedule(groupId: Int){
        screenModelScope.launch {
            val result = scheduleUseCase.getSchedule(groupId)
            result.collect{response ->
                when (response) {
                    is Either.Right -> {
                        state.update{
                            it.copy(
                                lessonsList = response.value,
                                success = true
                            )
                        }
                    }
                    is Either.Left -> {
                        state.update{
                            it.copy(
                                error = response.value.message
                            )
                        }
                    }
                }
            }
        }
    }
}