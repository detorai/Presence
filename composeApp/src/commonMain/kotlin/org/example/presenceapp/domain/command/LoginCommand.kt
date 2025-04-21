package org.example.presenceapp.domain.command

data class LoginCommand(
    val email: String,
    val password: String
)