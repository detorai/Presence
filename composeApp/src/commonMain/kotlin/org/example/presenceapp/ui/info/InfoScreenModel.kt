package org.example.presenceapp.ui.info

import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import org.example.presenceapp.data.common.dto.auth.UserResponseDto
import org.example.presenceapp.domain.entities.UserInfo

class InfoScreenModel: ScreenModel {
    val state = MutableStateFlow(InfoScreenState())

    fun getUserInfo(userResponseDto: UserResponseDto?){
        state.update {
            it.copy(
                userInfo = userResponseDto
            )
        }
    }

    val user = listOf(
        UserInfo.userGroup,
        UserInfo.userName,
        UserInfo.userRole
    )
}