package org.example.presenceapp.ui.info

import org.example.presenceapp.domain.entities.UserInfo

data class InfoScreenState(
    val userInfo: UserInfo? = null,
    var error: String? = null,
    var success: Boolean = false
)
