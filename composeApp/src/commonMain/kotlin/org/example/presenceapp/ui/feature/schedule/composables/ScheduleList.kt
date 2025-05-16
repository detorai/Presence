package org.example.presenceapp.ui.feature.schedule.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus
import org.example.presenceapp.ui.base.SIDE_EFFECTS_KEY
import org.example.presenceapp.ui.feature.commons.CommonTopBar
import org.example.presenceapp.ui.feature.commons.types.ScreenType
import org.example.presenceapp.ui.feature.schedule.ScheduleContract
import org.example.presenceapp.ui.theme.AppTheme
import org.example.project.domain.models.Week
import org.example.project.domain.models.formatDay

@Composable
fun Schedule(
    state: ScheduleContract.State,
    daysOfWeek: Map<String, LocalDate>,
    effectFlow: Flow<ScheduleContract.Effect>,
    onEventSent: (event: ScheduleContract.Event) -> Unit,
    ) {
    val pagerState = rememberPagerState(
        initialPage = state.currentDayIndex,
        pageCount = { daysOfWeek.size }
    )
    val currentPage = remember { derivedStateOf { pagerState.currentPage + pagerState.currentPageOffsetFraction } }
    val currentDay = state.weeks?.startDate?.plus(currentPage.value.toInt(), DateTimeUnit.DAY)
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(pagerState){
        snapshotFlow { pagerState.isScrollInProgress }.collect{
            onEventSent(ScheduleContract.Event.UpdateSelectedDay(pagerState.currentPage))
        }
    }
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effectFlow.collect { effect ->
            when (effect) {
                is ScheduleContract.Effect.ShowError -> {
                    snackbarHostState.showSnackbar(
                        message = effect.message!!,
                        duration = SnackbarDuration.Short
                    )
                }
                is ScheduleContract.Effect.Navigation.ToBack -> TODO()
                is ScheduleContract.Effect.Navigation.ToPresence -> TODO()
                is ScheduleContract.Effect.SelectedPageChanged -> {
                    if (!pagerState.isScrollInProgress) {
                        coroutineScope.launch {
                            pagerState.scrollToPage(effect.currentDayIndex)
                        }
                    }
                }
            }
        }
    }

    Scaffold(
        topBar = {
            CommonTopBar(
                screenType = ScreenType.SCHEDULE,
                text = daysOfWeek[daysOfWeek.keys.elementAt(state.currentDayIndex)].toString()
            )
        },
        snackbarHost = {
            SnackbarHost(
                snackbarHostState
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
        ) {
            ScheduleDaySelector(
                currentPage = currentPage.value,
                daysOfWeek = daysOfWeek.keys.toList(),
                onDaySelected = {
                    println(it)
                    onEventSent(ScheduleContract.Event.UpdateSelectedDay(it))
                },
                indicatorColor = AppTheme.colors.black
            )
            HorizontalPager(state = pagerState) { page ->
                val day = page + 1
                state.lessons[day]?.let { lessons ->
                    ScheduleLessonList(
                        lessons = lessons,
                        onLessonClick = { lesson ->
                            onEventSent(ScheduleContract.Event.SelectLesson(lesson))
                        }
                    )
                }
            }
        }
    }
}