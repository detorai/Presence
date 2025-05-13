package org.example.presenceapp.data.common

import org.example.presenceapp.data.common.dto.presence.PresettingDto
import org.example.presenceapp.data.common.dto.auth.AuthRequestDto
import org.example.presenceapp.data.common.dto.auth.AuthResponseDto
import org.example.presenceapp.data.common.dto.auth.GroupDto
import org.example.presenceapp.data.common.dto.auth.ResponsibleDto
import org.example.presenceapp.data.common.dto.auth.ResponsibleTypeDto
import org.example.presenceapp.data.common.dto.auth.RoleResponseDto
import org.example.presenceapp.data.common.dto.auth.UserResponseDto
import org.example.presenceapp.data.common.dto.group.GroupRequestDto
import org.example.presenceapp.data.common.dto.group.PresenceResponseDto
import org.example.presenceapp.data.common.dto.group.PresenceRowResponseDto
import org.example.presenceapp.data.common.dto.group.PresenceRowScheduleResponseDto
import org.example.presenceapp.data.common.dto.group.PresenceSubjectResponseDto
import org.example.presenceapp.data.common.dto.group.ScheduleResponseDto
import org.example.presenceapp.data.common.dto.group.StudentsResponseDto
import org.example.presenceapp.data.common.dto.group.SubjectResponseDto
import org.example.presenceapp.data.common.dto.group.SubjectsResponseDto
import org.example.presenceapp.data.common.dto.presence.AttendanceTypeDto
import org.example.presenceapp.data.common.dto.presence.PresenceRequestDto
import org.example.presenceapp.domain.command.GroupCommand
import org.example.presenceapp.domain.entities.GroupResponse
import org.example.presenceapp.domain.command.LoginCommand
import org.example.presenceapp.domain.command.PresenceCommand
import org.example.presenceapp.domain.entities.AttendanceTypeNet
import org.example.presenceapp.domain.entities.LoginResponse
import org.example.presenceapp.domain.entities.Presence
import org.example.presenceapp.domain.entities.PresenceRow
import org.example.presenceapp.domain.entities.PresenceRowSchedule
import org.example.presenceapp.domain.entities.PresenceSubject
import org.example.presenceapp.domain.entities.Presetting
import org.example.presenceapp.domain.entities.Responsible
import org.example.presenceapp.domain.entities.ResponsibleType
import org.example.presenceapp.domain.entities.RoleResponse
import org.example.presenceapp.domain.entities.Schedule
import org.example.presenceapp.domain.entities.ScheduleInfo
import org.example.presenceapp.domain.entities.Students
import org.example.presenceapp.domain.entities.Subject
import org.example.presenceapp.domain.entities.UserResponse

fun ScheduleResponseDto.toEntity(): Schedule = Schedule(
    scheduleInfo = subjects.map { it.toEntity() },
    dayOfWeek = dayOfWeek,
)
fun SubjectsResponseDto.toEntity(): ScheduleInfo = ScheduleInfo(
    audience = audience,
    subject = subject.toEntity(),
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

fun GroupCommand.toDto(): GroupRequestDto = GroupRequestDto(groupId)

fun PresenceResponseDto.toEntity(): Presence = Presence(
    presenceDate, subjects.map { it.toEntity() }
)
fun PresenceSubjectResponseDto.toEntity(): PresenceSubject = PresenceSubject(
    dayOfWeek, presenceRow.map { it.toEntity() }
)
fun PresenceRowResponseDto.toEntity(): PresenceRow = PresenceRow(
    presenceId, schedule.toEntity(), attendanceTypeId, studentId
)
fun PresenceRowScheduleResponseDto.toEntity(): PresenceRowSchedule = PresenceRowSchedule(
    id, lessonNumber, audience, subject.toEntity()
)

fun StudentsResponseDto.toEntity(): Students = Students(
    studentId, uuid, email, number, fio, enrollDate, expulsionDate
)

fun AttendanceTypeDto.toEntity(): AttendanceTypeNet = AttendanceTypeNet(
    id, name
)

fun PresettingDto.toEntity(): Presetting = Presetting(
    id, attendanceType, studentId, startAt, endAt
)

fun PresenceCommand.toDto(): PresenceRequestDto = PresenceRequestDto(
    studentId, scheduleId, attendanceTypeId, presenceDate
)

