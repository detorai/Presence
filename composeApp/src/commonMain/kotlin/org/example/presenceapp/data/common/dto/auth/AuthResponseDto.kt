package org.example.presenceapp.data.common.dto.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponseDto(
    val user: UserResponseDto,
    val token: String
)

@Serializable
data class UserResponseDto(
    val uuid: String,
    val email: String,
    val number: String,
    val fio: String,
    val role: RoleResponseDto,
    var responsible: List<ResponsibleDto> = emptyList()
)

@Serializable
data class RoleResponseDto(
    val id: Int,
    val name: String
)

@Serializable
data class GroupDto(
    val id: Int,
    val name: String
)

@Serializable
data class ResponsibleTypeDto(
    val id: Int,
    val name: String
)

@Serializable
data class ResponsibleDto(
    val group: GroupDto,
    val responsibleType: ResponsibleTypeDto
)