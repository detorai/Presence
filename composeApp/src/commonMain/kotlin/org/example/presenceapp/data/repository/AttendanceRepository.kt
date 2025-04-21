package org.example.presenceapp.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import org.example.presenceapp.data.local.LocalDataSource
import org.example.presenceapp.domain.entities.AbsenceReason
import org.example.presenceapp.domain.entities.Attendance

class AttendanceRepository(
    private val localDataSource: LocalDataSource
) {
    suspend fun saveAttendanceLocally(attendance: Map<String, Attendance>) {
        localDataSource.saveAttendance(attendance)
    }

    fun observeLocalAttendance(): Flow<Map<String, Attendance>> {
        return localDataSource.observeAttendance()
            .map { attendanceMap ->
                attendanceMap
            }
            .catch { e ->
                emit(emptyMap())
            }
    }

    suspend fun syncWithServer() {

    }

    suspend fun updateAbsenceReason(studentId: String, reason: AbsenceReason) {
        val currentAttendance = localDataSource.getCurrentAttendance().toMutableMap()
        currentAttendance[studentId]?.let { attendance ->
            currentAttendance[studentId] = attendance.copy(
                absenceReason = reason.name,
                isModified = true
            )
            localDataSource.saveAttendance(currentAttendance)
        }
    }
}

class AttendanceSyncException(message: String, cause: Throwable) : Exception(message, cause)