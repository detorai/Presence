package org.example.presenceapp.ui.feature.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.example.presenceapp.ui.feature.calendar.components.CalendarGrid
import org.example.presenceapp.ui.feature.calendar.components.MonthHeader
import org.example.presenceapp.ui.feature.calendar.components.WeekDaysHeader
import org.example.presenceapp.ui.feature.commons.CommonBottomBar
import org.example.presenceapp.ui.theme.AppTheme

class CalendarScreen : Screen {
    @Composable
    override fun Content() {
        val screenModel = remember { CalendarScreenModel() }

        Calendar(screenModel = screenModel)
    }
}

@Composable
fun Calendar(screenModel: CalendarScreenModel) {
    val navigator = LocalNavigator.currentOrThrow
    val viewModel = remember { screenModel }
    val currentDate = remember { Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date }
    val monthData by viewModel.monthData.collectAsState()

    LaunchedEffect(currentDate) {
        viewModel.loadMonthData(currentDate.year, currentDate.monthNumber)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { CommonBottomBar() }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = AppTheme.colors.white)
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            MonthHeader(
                month = currentDate.month,
                year = currentDate.year,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            WeekDaysHeader(
                modifier = Modifier
                    .padding(bottom = 8.dp)
            )

            CalendarGrid(
                monthData = monthData,
                currentDate = currentDate,
                onDayClick = { date ->
                    viewModel.toggleAttendance(date)
                }
            )
        }
    }
}