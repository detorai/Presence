package org.example.presenceapp.ui.attendance

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.example.presenceapp.domain.common.SomeStudents
import org.example.presenceapp.data.repository.AttendanceRepository
import org.example.presenceapp.domain.entities.AbsenceReason
import org.example.presenceapp.domain.entities.Attendance
import org.example.presenceapp.domain.entities.AttendanceType
import org.example.presenceapp.ui.attendance.components.toReadableString
import org.example.presenceapp.ui.settings.SettingsManager
import org.example.presenceapp.ui.settings.SettingsScreenModel

class AttendanceScreenModel(
    private val attendanceRepository: AttendanceRepository,
    private val settingsScreenModel: SettingsScreenModel,
    private val settingsManager: SettingsManager
) : ScreenModel {

    private val _students = MutableStateFlow(SomeStudents().students)
    private val _attendanceMap = MutableStateFlow<Map<String, Attendance>>(emptyMap())
    private val _sortType = MutableStateFlow(AttendanceType.PRESENT)
    private val _defaultStatus: StateFlow<AttendanceType> = settingsScreenModel.defaultStatus

    val attendanceMap: StateFlow<Map<String, Attendance>> = _attendanceMap.asStateFlow()

    val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date

    private val _showDialog = mutableStateOf(false)
    val showDialog: State<Boolean> = _showDialog

    fun checkDialogState() {
        if (!settingsManager.isDialogShown()) {
            _showDialog.value = true
            println("Dialog should be shown")
        } else {
            println("Dialog already shown, not showing again")
        }
    }

    fun onDialogDismissed() {
        _showDialog.value = false
        settingsManager.setDialogShown()
    }

    companion object {
        const val PRESENT = "присут"
        const val ABSENT = "отсут"

        fun Attendance.toDisplayStatus(): String {
            return when (type) {
                AttendanceType.PRESENT -> PRESENT
                AttendanceType.ABSENT -> absenceReason?.toReadableString() ?: ABSENT
            }
        }
    }

    init {
        loadAttendance()
    }

    private fun String.toAttendanceType(): AttendanceType {
        return when (this) {
            PRESENT -> AttendanceType.PRESENT
            else -> AttendanceType.ABSENT
        }
    }

    private fun loadAttendance() {
        screenModelScope.launch {
            attendanceRepository.observeLocalAttendance().combine(settingsScreenModel.defaultStatus) { savedAttendance, defaultStatusValue ->
                val students = _students.value
                students.associate { student ->
                    val saved = savedAttendance[student.id]
                    if (saved != null) {
                        student.id to saved
                    } else {
                        student.id to Attendance(
                            studentId = student.id.toString(),
                            date = today,
                            type = defaultStatusValue,
                            isModified = false,
                            absenceReason = if (defaultStatusValue == AttendanceType.ABSENT) AbsenceReason.SKIP.name else null
                        )
                    }
                }
            }.collect { newMap ->
                _attendanceMap.value = newMap
            }
        }
    }

    val groupedStudents = combine(
        _students,
        _attendanceMap,
        _sortType
    ) { students, attendance, sortType ->
        val present = students.filter { attendance[it.id]?.type == AttendanceType.PRESENT }.sortedBy { it.name }
        val absent = students.filter { attendance[it.id]?.type == AttendanceType.ABSENT }.sortedBy { it.name }

        when (sortType) {
            AttendanceType.PRESENT -> listOf("Присутствующие" to present, "Отсутствующие" to absent)
            AttendanceType.ABSENT -> listOf("Отсутствующие" to absent, "Присутствующие" to present)
        }
    }

    fun updateDefaultStatus(type: AttendanceType) {
        settingsScreenModel.updateDefaultStatus(type)

        val updatedMap = _attendanceMap.value.toMutableMap().apply {
            entries.forEach { (studentId, attendance) ->
                if (!attendance.isModified) {
                    this[studentId] = attendance.copy(type = type)
                }
            }
        }
        _attendanceMap.value = updatedMap
        screenModelScope.launch {
            saveAttendanceToStorage(updatedMap)
        }
    }

    fun updateAttendance(studentId: String, type: AttendanceType) {
        val updatedMap = _attendanceMap.value.toMutableMap().apply {
            this[studentId] = Attendance(
                studentId = studentId.toString(),
                date = today,
                type = type,
                isModified = true,
                absenceReason = if (type == AttendanceType.ABSENT) AbsenceReason.SKIP.name else null
            )
        }
        _attendanceMap.value = updatedMap
        screenModelScope.launch {
            attendanceRepository.saveAttendanceLocally(updatedMap)
        }
    }

    fun updateAttendanceForSelected(studentIds: Set<String>, type: AttendanceType) {
        val updatedMap = _attendanceMap.value.toMutableMap().apply {
            studentIds.forEach { studentId ->
                this[studentId] = Attendance(
                    studentId = studentId.toString(),
                    date = today,
                    type = type,
                    isModified = true,
                    absenceReason = if (type == AttendanceType.ABSENT) AbsenceReason.SKIP.name else null
                )
            }
        }
        _attendanceMap.value = updatedMap
        screenModelScope.launch {
            attendanceRepository.saveAttendanceLocally(updatedMap)
        }
    }

    fun changeSortType(newSortType: AttendanceType) {
        _sortType.value = newSortType
    }

    private suspend fun saveAttendanceToStorage(map: Map<String, Attendance>) {
        attendanceRepository.saveAttendanceLocally(map)
    }

    fun updateAbsenceReason(studentId: String, reason: String) {
        val updatedMap = _attendanceMap.value.toMutableMap().apply {
            val attendance = this[studentId]
            if (attendance != null && attendance.type == AttendanceType.ABSENT) {
                this[studentId] = attendance.copy(absenceReason = reason, isModified = true)
            }
        }
        screenModelScope.launch {
            saveAttendanceToStorage(updatedMap)
            _attendanceMap.emit(updatedMap)
        }
    }
}