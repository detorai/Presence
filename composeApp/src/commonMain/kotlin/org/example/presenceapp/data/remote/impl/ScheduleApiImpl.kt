package org.example.presenceapp.data.remote.impl

import org.example.presenceapp.data.common.dto.group.StudentResponseDto
import org.example.presenceapp.data.common.dto.schedule.ScheduleResponseDto
import org.example.presenceapp.data.remote.api.GroupApi

class ScheduleApiImpl(private val groupApi: GroupApi) {
    suspend fun getSchedule(groupId: Int): List<ScheduleResponseDto> = groupApi.getSchedule(groupId)
    suspend fun getStudent(groupId: Int): List<StudentResponseDto> = groupApi.getStudents(groupId)
}