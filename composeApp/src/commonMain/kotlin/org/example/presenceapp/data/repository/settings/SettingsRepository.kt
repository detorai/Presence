package org.example.presenceapp.data.repository.settings

import org.example.presenceapp.domain.entities.AttendanceType

interface SettingsRepository {
    suspend fun getDefaultAttendanceStatus(): AttendanceType
    suspend fun setDefaultAttendanceStatus(type: AttendanceType)
}