package org.example.presenceapp.data.remote.impl

import org.example.presenceapp.data.common.dto.attendance.AttendanceResponseDto
import org.example.presenceapp.data.common.dto.group.StudentResponseDto
import org.example.presenceapp.data.common.dto.schedule.ScheduleRequestDto
import org.example.presenceapp.data.common.dto.schedule.ScheduleResponseDto
import org.example.presenceapp.data.remote.api.GroupApi

class ScheduleApiImpl(private val groupApi: GroupApi) {
    suspend fun getSchedule(scheduleRequestDto: ScheduleRequestDto): List<ScheduleResponseDto> = groupApi.getSchedule(scheduleRequestDto.groupId)
    suspend fun getStudent(scheduleRequestDto: ScheduleRequestDto): List<StudentResponseDto> = groupApi.getStudents(scheduleRequestDto.groupId)
    suspend fun getPresence(scheduleRequestDto: ScheduleRequestDto): List<AttendanceResponseDto> = groupApi.getPresence(scheduleRequestDto.groupId)
}