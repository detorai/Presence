package org.example.presenceapp.ui.settings

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import mu.KLogger
import org.example.presenceapp.data.repository.settings.SettingsRepository
import org.example.presenceapp.domain.entities.AttendanceType

class SettingsScreenModel(
    private val settingsRepository: SettingsRepository
): ScreenModel {
    private val logger: KLogger = mu.KotlinLogging.logger {}

    private val _defaultStatus = MutableStateFlow<AttendanceType>(AttendanceType.ABSENT)
    val defaultStatus: StateFlow<AttendanceType> = _defaultStatus.asStateFlow()

    init {
        loadSettings()
    }

    private fun loadSettings() {
        screenModelScope.launch {
            _defaultStatus.value = settingsRepository.getDefaultAttendanceStatus()
        }
    }

    fun updateDefaultStatus(type: AttendanceType) {
        screenModelScope.launch {
            logger.debug { "Saving default status: $type" }
            settingsRepository.setDefaultAttendanceStatus(type)
            _defaultStatus.value = type
        }
    }
}

//class SettingsScreenModel(
//    private val settingsRepository: SettingsRepository,
//    attendanceScreenModel: Lazy<AttendanceScreenModel>
//) : ScreenModel {
//    private val attendanceScreenModel: AttendanceScreenModel by attendanceScreenModel
//    private val _defaultStatus = MutableStateFlow(AttendanceType.ABSENT)
//    val defaultStatus = _defaultStatus.asStateFlow()
//
//    private val logger = KotlinLogging.logger {}
//    private fun logError(message: String, e: Throwable) {
//        logger.error(e) { message }
//    }
//
//    init {
//        try {
//            loadSettings()
//        } catch (e: Exception) {
//            logError("Ошибка инициализации", e)
//        }
//    }
//
//    private fun loadSettings() {
//        screenModelScope.launch {
//            val status = settingsRepository.getDefaultAttendanceStatus()
//            _defaultStatus.value = status
//            attendanceScreenModel.updateDefaultStatus(status)
//        }
//    }
//
//    fun updateDefaultStatus(type: AttendanceType) {
//        screenModelScope.launch {
//            settingsRepository.setDefaultAttendanceStatus(type)
//            _defaultStatus.value = type
//            attendanceScreenModel.updateDefaultStatus(type)
//        }
//    }
//}