package org.example.project.ui.weeks

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.example.presenceapp.ui.feature.commons.CommonBottomBar
import org.example.presenceapp.ui.feature.schedule.ScheduleScreen
import org.example.presenceapp.ui.theme.AppTheme
import org.example.presenceapp.ui.feature.weeks.components.MonthHeader
import org.example.presenceapp.ui.feature.weeks.components.ScheduleCard
import org.example.project.domain.models.formatWeek

class WeeksScreen: Screen {
    @Composable
    override fun Content() {
        val navigator  = LocalNavigator.currentOrThrow
        val viewModel = rememberScreenModel { WeeksViewModel() }
        Scaffold(
            bottomBar = {
                CommonBottomBar()
            },
        ) {
            Weeks(viewModel, navigator)
        }
    }


    @Composable
    fun Weeks(viewModel: WeeksViewModel, navigator: Navigator){
        val state = viewModel.state.collectAsState().value

        Column(
            modifier = Modifier.fillMaxSize().background(AppTheme.colors.white).padding(32.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ){
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
                        ScheduleCard(text = week.formatWeek(), onClick = {
                            navigator.push(ScheduleScreen(week))
                        })
                        Spacer(Modifier.height(10.dp))
                    }
                }
            }
        }
    }
}