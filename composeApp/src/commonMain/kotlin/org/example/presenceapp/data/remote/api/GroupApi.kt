package org.example.presenceapp.data.remote.api

import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Path
import org.example.presenceapp.data.common.dto.attendance.AttendanceResponseDto
import org.example.presenceapp.data.common.dto.group.StudentResponseDto
import org.example.presenceapp.data.common.dto.schedule.ScheduleResponseDto

interface GroupApi {
    @GET("api/v1/group/{id}/schedule")
    suspend fun getSchedule(@Path id: Int): List<ScheduleResponseDto>

    @GET("api/v1/group/{id}/students")
    suspend fun getStudents(@Path id: Int): List<StudentResponseDto>

    @GET("api/v1/group/{id}/presence")
    suspend fun getPresence(@Path id: Int): List<AttendanceResponseDto>
}