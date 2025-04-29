package org.example.presenceapp.data.remote.api

import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.POST
import org.example.presenceapp.data.common.dto.group.GroupRequestDto
import org.example.presenceapp.data.common.dto.group.PresenceResponseDto
import org.example.presenceapp.data.common.dto.presence.PresettingDto
import org.example.presenceapp.data.common.dto.presence.AttendanceTypeDto
import org.example.presenceapp.data.common.dto.presence.PresenceRequestDto

interface PresenceApi {
    @GET("api/v1/presence/dictionary/attendance_type")
    suspend fun getAttendanceTypes(): List<AttendanceTypeDto>

    @GET("api/v1/presence/presetting/{group_id}")
    suspend fun getPresetting(groupRequestDto: GroupRequestDto): List<PresettingDto>

    @POST("api/v1/presence")
    suspend fun sendPresence(@Body presenceRequestDto: List<PresenceRequestDto>): List<PresenceResponseDto>
}