package org.example.presenceapp.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import org.example.presenceapp.data.local.storage.SettingsStorage
import org.example.presenceapp.data.repository.settings.SettingsRepositoryImpl
import org.example.presenceapp.getPlatformContext
import org.example.presenceapp.ui.commons.CommonBottomBar
import org.example.presenceapp.ui.settings.components.SettingsReasonOption
import org.example.presenceapp.ui.theme.AppTheme

class SettingsScreen: Screen {
    @Composable
    override fun Content() {
        val platformContext = getPlatformContext()
        val settingsRepository = SettingsRepositoryImpl(settingsStorage = SettingsStorage(platformContext))
        val settingsScreenModel = remember { SettingsScreenModel(settingsRepository = settingsRepository) }

        Settings(settingsScreenModel)
    }
}

@Composable
fun Settings(settingsScreenModel: SettingsScreenModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.white)
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = { CommonBottomBar() }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                SettingsReasonOption(
                    onReasonSelected = {  }
                )
            }
        }
    }
}