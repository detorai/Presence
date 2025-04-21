package org.example.presenceapp.data.local

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import org.example.presenceapp.data.local.storage.attendance.AttendanceStorage
import org.example.presenceapp.domain.entities.AbsenceReason
import org.example.presenceapp.domain.entities.Attendance
import org.example.presenceapp.domain.entities.AttendanceType

class LocalDataSource(private val attendanceStorage: AttendanceStorage) {
    suspend fun saveAttendance(map: Map<String, Attendance>) {
        attendanceStorage.saveAttendanceMap(map)
    }

    fun observeAttendance(): Flow<Map<String, Attendance>> {
        return attendanceStorage.attendanceMapFlow()
            .map { attendanceMap ->
                attendanceMap
            }
            .catch { e ->
                println("Error reading attendance: ${e.message}")
                emit(emptyMap())
            }
    }

    suspend fun getCurrentAttendance(): Map<String, Attendance> {
        return try {
            attendanceStorage.attendanceMapFlow().first()
        } catch (e: Exception) {
            println("Error getting current attendance: ${e.message}")
            emptyMap()
        }
    }

    suspend fun updateAbsenceReason(studentId: String, reason: AbsenceReason) {
        val current = getCurrentAttendance().toMutableMap()
        current[studentId]?.let { attendance ->
            current[studentId] = attendance.copy(
                absenceReason = reason.name,
                isModified = true
            )
            saveAttendance(current)
        }
    }

    suspend fun clearAttendance() {
        saveAttendance(emptyMap())
    }

    suspend fun getAttendanceForStudent(studentId: String): Attendance? {
        return getCurrentAttendance()[studentId]
    }

    suspend fun getAbsentStudents(): Map<String, Attendance> {
        return getCurrentAttendance()
            .filterValues { it.type == AttendanceType.ABSENT }
    }
}