package org.example.presenceapp.data.common

import org.example.presenceapp.data.common.dto.auth.AuthRequestDto
import org.example.presenceapp.data.common.dto.auth.AuthResponseDto
import org.example.presenceapp.data.common.dto.auth.GroupDto
import org.example.presenceapp.data.common.dto.auth.ResponsibleDto
import org.example.presenceapp.data.common.dto.auth.ResponsibleTypeDto
import org.example.presenceapp.data.common.dto.auth.RoleResponseDto
import org.example.presenceapp.data.common.dto.auth.UserResponseDto
import org.example.presenceapp.data.common.dto.schedule.ScheduleRequestDto
import org.example.presenceapp.data.common.dto.schedule.ScheduleResponseDto
import org.example.presenceapp.data.common.dto.schedule.SubjectResponseDto
import org.example.presenceapp.domain.command.GroupCommand
import org.example.presenceapp.domain.entities.GroupResponse
import org.example.presenceapp.domain.command.LoginCommand
import org.example.presenceapp.domain.entities.LoginResponse
import org.example.presenceapp.domain.entities.Responsible
import org.example.presenceapp.domain.entities.ResponsibleType
import org.example.presenceapp.domain.entities.RoleResponse
import org.example.presenceapp.domain.entities.Schedule
import org.example.presenceapp.domain.entities.Subject
import org.example.presenceapp.domain.entities.UserResponse

fun ScheduleResponseDto.toEntity(): Schedule = Schedule(
    subject = subject.toEntity(),
    audience = audience,
    dayOfWeek = dayOfWeek,
    lessonNumber = lessonNumber,
    id = id
)

fun SubjectResponseDto.toEntity(): Subject = Subject(
    id = id,
    name = name
)

fun LoginCommand.toDto(): AuthRequestDto = AuthRequestDto(
    login = email,
    password = password
)

fun AuthResponseDto.toEntity(): LoginResponse = LoginResponse(
    token = token,
    user = user.toEntity()
)

fun UserResponseDto.toEntity(): UserResponse = UserResponse(
    uuid = uuid,
    email = email,
    number = number,
    fio = fio,
    role = role.toEntity(),
    responsible = responsible.map { it.toEntity() }
)

fun ResponsibleDto.toEntity(): Responsible = Responsible(
    group = group.toEntity(),
    responsibleType = responsibleType.toEntity()
)

fun GroupDto.toEntity(): GroupResponse = GroupResponse(
    id = id,
    name = name
)

fun ResponsibleTypeDto.toEntity(): ResponsibleType = ResponsibleType(
    id = id,
    name = name
)

fun RoleResponseDto.toEntity(): RoleResponse = RoleResponse(
    id = id,
    name = name
)

fun GroupCommand.toDto(): ScheduleRequestDto = ScheduleRequestDto(groupId)


