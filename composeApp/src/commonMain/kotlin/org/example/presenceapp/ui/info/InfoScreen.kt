package org.example.presenceapp.ui.info

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import org.example.presenceapp.ui.commons.CommonBottomBar
import org.example.presenceapp.ui.commons.ErrorDialog
import org.example.presenceapp.ui.info.components.InfoCard
import org.example.presenceapp.ui.theme.AppTheme

class InfoScreen(): Screen {
    @Composable
    override fun Content() {
        val viewModel = koinScreenModel<InfoScreenModel>()
        Info(viewModel)
    }
    @Composable
    fun Info(viewModel: InfoScreenModel) {
        val state = viewModel.state.collectAsState().value
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = { CommonBottomBar() }
        ) { padding ->

            Column(
                modifier = Modifier.fillMaxSize().background(AppTheme.colors.white).padding(32.dp)
            ) {
                state.error?.let {
                    ErrorDialog(
                        onDismiss = viewModel::resetError,
                        text = it
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        "Информация",
                        modifier = Modifier.padding(top = 10.dp),
                        color = AppTheme.colors.black,
                        style = AppTheme.typography.main
                    )
                }
                Column(
                    modifier = Modifier.fillMaxWidth().padding(top = 43.dp)
                ) {
                    InfoCard(
                        onClick = {},
                        text = state.userInfo?.userName.toString()
                    )
                    Spacer(Modifier.height(10.dp))
                    InfoCard(
                        onClick = {},
                        text = state.userInfo?.userGroup.toString()
                    )
                    Spacer(Modifier.height(10.dp))
                    InfoCard(
                        onClick = {},
                        text = state.userInfo?.userRole.toString()
                    )
                    Spacer(Modifier.height(10.dp))
                }
            }
        }
    }
}



