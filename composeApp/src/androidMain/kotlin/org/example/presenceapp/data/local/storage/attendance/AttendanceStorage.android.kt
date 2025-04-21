package org.example.presenceapp.data.local.storage.attendance

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json
import org.example.presenceapp.domain.entities.Attendance

private val Context.attendanceDataStore by preferencesDataStore(name = "attendance_prefs")

class AttendanceStorageAndroid(private val context: Context): AttendanceStorage {

    private val ATTENDANCE_KEY = stringPreferencesKey("attendance_map")

    override suspend fun saveAttendanceMap(map: Map<String, Attendance>) {
        val json = Json.encodeToString(map)
        context.attendanceDataStore.edit { prefs ->
            prefs[ATTENDANCE_KEY] = json
        }
    }

    override fun attendanceMapFlow(): Flow<Map<String, Attendance>> {
        return context.attendanceDataStore.data.map { prefs ->
            prefs[ATTENDANCE_KEY]?.let {
                Json.decodeFromString<Map<String, Attendance>>(it)
            } ?: emptyMap()
        }
    }
}