package org.example.presenceapp.ui.info

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.presenceapp.domain.entities.Either
import org.example.presenceapp.domain.repo.LoginRepository
import org.example.presenceapp.domain.usecases.LoginUseCase

class InfoScreenModel(
    loginRepository: LoginRepository
): ScreenModel {
    val state = MutableStateFlow(InfoScreenState())

    private val loginUseCase = LoginUseCase(loginRepository)

    init {
        setUserInfo()
    }
    fun resetError(){
        state.update{
            it.copy(
                error = null
            )
        }
    }

    private fun setUserInfo(){
        screenModelScope.launch {
            val result = loginUseCase.getUserInfo()
            result.collect{ response ->
                when (response) {
                    is Either.Right -> {
                        state.update {
                            it.copy(
                                userInfo = response.value,
                                success = true
                            )
                        }
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
}