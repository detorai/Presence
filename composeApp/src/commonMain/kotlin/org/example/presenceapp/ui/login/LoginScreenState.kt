package org.example.project.ui.login

data class LoginScreenState(
    var login: String = "",
    var password: String = "",
    var error: String? = null,
    var success: Boolean = false,
    var check: Boolean = false,
)
