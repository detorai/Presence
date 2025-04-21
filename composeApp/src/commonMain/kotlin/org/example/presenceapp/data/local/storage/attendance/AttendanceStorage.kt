package org.example.presenceapp.data.local.storage.attendance

import kotlinx.coroutines.flow.Flow
import org.example.presenceapp.domain.entities.Attendance

interface AttendanceStorage {
    suspend fun saveAttendanceMap(map: Map<String, Attendance>)
    fun attendanceMapFlow(): Flow<Map<String, Attendance>>
}