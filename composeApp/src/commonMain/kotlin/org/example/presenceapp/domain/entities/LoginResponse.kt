package org.example.presenceapp.domain.entities

data class LoginResponse (
    val user: UserResponse,
    val token: String
)

data class UserResponse(
    val uuid: String,
    val email: String,
    val number: String,
    val fio: String,
    val role: RoleResponse,
    var responsible: List<Responsible>
)

data class RoleResponse(
    val id: Int,
    val name: String
)

data class GroupResponse(
    val id: Int,
    val name: String
)

data class ResponsibleType(
    val id: Int,
    val name: String
)

data class Responsible(
    val group: GroupResponse,
    val responsibleType: ResponsibleType
)