package org.example.presenceapp.data.remote.impl

import org.example.presenceapp.data.common.dto.group.GroupRequestDto
import org.example.presenceapp.data.common.dto.presence.AttendanceTypeDto
import org.example.presenceapp.data.common.dto.presence.PresenceRequestDto
import org.example.presenceapp.data.common.dto.presence.PresettingDto
import org.example.presenceapp.data.remote.api.PresenceApi

class PresenceApiImpl(private val presenceApi: PresenceApi) {
    suspend fun getAttendanceType(): List<AttendanceTypeDto> = presenceApi.getAttendanceTypes()
    suspend fun getPresetting(groupRequestDto: GroupRequestDto): List<PresettingDto> = presenceApi.getPresetting(groupRequestDto)
    suspend fun sendPresence(presenceRequestDto: List<PresenceRequestDto>) = presenceApi.sendPresence(presenceRequestDto)
}