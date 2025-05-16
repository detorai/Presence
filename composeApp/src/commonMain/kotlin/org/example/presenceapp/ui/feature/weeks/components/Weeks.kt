package org.example.presenceapp.ui.feature.weeks.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.Flow
import org.example.presenceapp.ui.base.SIDE_EFFECTS_KEY
import org.example.presenceapp.ui.feature.commons.CommonBottomBar
import org.example.presenceapp.ui.theme.AppTheme
import org.example.project.domain.models.formatWeek
import org.example.project.ui.login.LoginContract
import org.example.project.ui.weeks.WeekContract
import org.example.project.ui.weeks.WeeksViewModel

@Composable
fun Weeks(
    state: WeekContract.State,
    effectFlow: Flow<WeekContract.Effect>,
    onEventSent: (event: WeekContract.Event) -> Unit,
    onNavigationRequested: (WeekContract.Effect.Navigation) -> Unit
){
    val snackbarState = remember { SnackbarHostState() }
    LaunchedEffect(SIDE_EFFECTS_KEY){
        effectFlow.collect { effect ->
            when (effect) {
                is WeekContract.Effect.ShowError -> {
                    snackbarState.showSnackbar(
                        message = effect.message!!,
                        duration = SnackbarDuration.Indefinite
                    )
                }
                is WeekContract.Effect.Navigation -> {
                    onNavigationRequested(effect)
                }
            }
        }
    }
    Scaffold(
        bottomBar = {
            CommonBottomBar()
        },
        snackbarHost = {
            SnackbarHost(snackbarState)
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize().background(AppTheme.colors.white).padding(32.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "Расписание",
                    modifier = Modifier.padding(top = 10.dp),
                    color = AppTheme.colors.black,
                    style = AppTheme.typography.main
                )
            }
            LazyColumn(
                modifier = Modifier.padding(top = 43.dp)
            ) {
                state.data.forEach { (month, year, weeks) ->
                    item {
                        MonthHeader(month = month)
                        Spacer(Modifier.height(10.dp))
                    }
                    items(weeks.sortedByDescending { it.startDate }) { week ->
                        ScheduleCard(
                            text = week.formatWeek(),
                            onClick = {
                                onEventSent(WeekContract.Event.WeekSelected(week))
                            }
                        )
                        Spacer(Modifier.height(10.dp))
                    }
                }
            }
        }
    }
}