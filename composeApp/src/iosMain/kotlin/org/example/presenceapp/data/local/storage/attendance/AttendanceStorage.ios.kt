package org.example.presenceapp.data.local.storage.attendance

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import org.example.presenceapp.domain.entities.Attendance
import platform.Foundation.NSUserDefaults

class AttendanceStorageIos: AttendanceStorage {

    private val defaults = NSUserDefaults.standardUserDefaults

    override suspend fun saveAttendanceMap(map: Map<String, Attendance>) {
        val json = Json.encodeToString(map)
        defaults.setObject(json, forKey = "attendance_map")
    }

    override fun attendanceMapFlow(): Flow<Map<String, Attendance>> {
        return flow {
            val json = defaults.stringForKey("attendance_map")
            emit(
                if (json != null) {
                    Json.decodeFromString<Map<String, Attendance>>(json)
                } else {
                    emptyMap()
                }
            )
        }
    }
}