package org.example.presenceapp.data.remote.impl

import org.example.presenceapp.data.common.dto.presence.PresettingDto
import org.example.presenceapp.data.common.dto.group.GroupRequestDto
import org.example.presenceapp.data.common.dto.group.PresenceResponseDto
import org.example.presenceapp.data.common.dto.group.ScheduleResponseDto
import org.example.presenceapp.data.common.dto.group.StudentsResponseDto
import org.example.presenceapp.data.remote.api.GroupApi

class GroupApiImpl(private val groupApi: GroupApi) {
    suspend fun getSchedule(groupRequestDto: GroupRequestDto): List<ScheduleResponseDto> = groupApi.getSchedule(groupRequestDto.groupId)
    suspend fun getStudents(groupRequestDto: GroupRequestDto): List<StudentsResponseDto> = groupApi.getStudents(groupRequestDto.groupId)
    suspend fun getPresence(groupRequestDto: GroupRequestDto): List<PresenceResponseDto> = groupApi.getPresence(groupRequestDto.groupId)
}