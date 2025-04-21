package org.example.presenceapp.data.repository.settings

import org.example.presenceapp.data.local.storage.SettingsStorage
import org.example.presenceapp.domain.entities.AttendanceType

class SettingsRepositoryImpl(
    private val settingsStorage: SettingsStorage
) : SettingsRepository {

    override suspend fun getDefaultAttendanceStatus(): AttendanceType {
        val statusString = settingsStorage.get(
            key = "default_attendance_status",
            defaultValue = AttendanceType.ABSENT.name
        )
        return enumValueOf(statusString)
    }

    override suspend fun setDefaultAttendanceStatus(type: AttendanceType) {
        settingsStorage.put(
            key = "default_attendance_status",
            value = type.name
        )
    }
}