package org.example.presenceapp.ui.feature.attendance

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import org.example.presenceapp.data.local.LocalDataSource
import org.example.presenceapp.data.local.storage.SettingsStorage
import org.example.presenceapp.data.local.storage.attendance.AttendanceStorageProvider
import org.example.presenceapp.data.repository.AttendanceRepository
import org.example.presenceapp.data.repository.settings.SettingsRepository
import org.example.presenceapp.data.repository.settings.SettingsRepositoryImpl
import org.example.presenceapp.domain.common.SelectedLessonHolder
import org.example.presenceapp.domain.entities.Schedule
import org.example.presenceapp.getPlatformContext
import org.example.presenceapp.ui.feature.attendance.components.AttendanceColumn
import org.example.presenceapp.ui.feature.commons.CommonTopBar
import org.example.presenceapp.ui.feature.settings.Preset
import org.example.presenceapp.ui.feature.settings.SettingsScreenModel
import org.example.presenceapp.ui.feature.settings.getSettingsManager
import org.example.presenceapp.ui.theme.AppTheme
import org.example.presenceapp.ui.feature.commons.types.ScreenType

class AttendanceScreen(private val selectedLesson: Schedule): Screen {
    @Composable
    override fun Content() {
        val platformContext = getPlatformContext()
        val attendanceStorage = AttendanceStorageProvider(platformContext).provide()
        val localDataSource = LocalDataSource(attendanceStorage)
        val attendanceRepository = AttendanceRepository(localDataSource)
        val settingsStorage = SettingsStorage(platformContext)
        val settingsRepository: SettingsRepository = SettingsRepositoryImpl(settingsStorage)
        val settingsScreenModel = SettingsScreenModel(settingsRepository = settingsRepository)
        val settingsManager = getSettingsManager()
        val screenModel = rememberScreenModel {
            AttendanceScreenModel(
                attendanceRepository = attendanceRepository,
                settingsScreenModel = settingsScreenModel,
                settingsManager = settingsManager
            )
        }

        Attendance(
            screenModel = screenModel,
            selectedLesson = selectedLesson
        )
    }
}

@Composable
fun Attendance(screenModel: AttendanceScreenModel,
               selectedLesson: Schedule
) {
    SelectedLessonHolder.selectedLesson = selectedLesson
    val showDialog by screenModel.showDialog

    LaunchedEffect(Unit) {
        screenModel.checkDialogState()
        println("Dialog state checked: $showDialog")
    }

    if (showDialog) {
        println("Dialog will be shown")
        key(showDialog) {
            Preset(
                onDismiss = { screenModel.onDialogDismissed() },
                onStatusSelected = { selectedStatus ->
                    screenModel.updateDefaultStatus(selectedStatus)
                }
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.white)
    ) {
        Scaffold(
            topBar = { CommonTopBar(
                screenType = ScreenType.GROUP,
                text = "SelectedLessonHolder.selectedLesson?.scheduleInfo?.subject?.name ?: ",
                onChangeSortType = { newSortType ->
                    screenModel.changeSortType(newSortType)
                }
            ) }
        ) { padding ->
            AttendanceColumn(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                screenModel = screenModel
            )
        }
    }
}