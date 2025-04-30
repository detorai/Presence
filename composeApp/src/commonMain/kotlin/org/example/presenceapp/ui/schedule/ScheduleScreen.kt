package org.example.presenceapp.ui.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.plus
import org.example.presenceapp.ui.attendance.AttendanceScreen
import org.example.presenceapp.ui.commons.CommonTopBar
import org.example.presenceapp.ui.commons.ErrorDialog
import org.example.presenceapp.ui.commons.types.ScreenType
import org.example.presenceapp.ui.schedule.components.ScheduleDaySelector
import org.example.presenceapp.ui.schedule.components.ScheduleLessonList
import org.example.presenceapp.ui.theme.AppTheme
import org.example.project.domain.models.Week
import org.example.project.domain.models.formatDay

data class ScheduleScreen(
    private val week: Week,
): Screen {
    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<ScheduleScreenModel>()

        Schedule(screenModel = screenModel)

    }

    @Composable
    fun Schedule(screenModel: ScheduleScreenModel) {
        val navigator = LocalNavigator.currentOrThrow
        val state = screenModel.state.collectAsState().value
        val daysOfWeek = listOf("Пн", "Вт", "Ср", "Чт", "Пт", "Сб")
        val currentDayIndex by screenModel.currentDayIndex.collectAsState()
        val pagerState = rememberPagerState(
            initialPage = currentDayIndex,
            pageCount = { daysOfWeek.size }
        )
        val currentPage = pagerState.currentPage + pagerState.currentPageOffsetFraction
        val currentDay = week.startDate.plus(currentPage.toInt(), DateTimeUnit.DAY)


        LaunchedEffect(pagerState) {
            snapshotFlow { pagerState.isScrollInProgress }
                .collect { isScrolling ->
                    screenModel.setSwipeState(isScrolling)
                    if (!isScrolling) {
                        screenModel.selectDay(pagerState.currentPage)
                    }
                }
        }
        LaunchedEffect(currentDayIndex) {
            if (!pagerState.isScrollInProgress) {
                pagerState.animateScrollToPage(currentDayIndex)
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colors.white)
        ) {
            Scaffold(
                topBar = {
                    CommonTopBar(
                        screenType = ScreenType.SCHEDULE,
                        text = currentDay.formatDay()
                    )
                },
            ) { padding ->
                Column(
                    modifier = Modifier
                        .padding(padding)
                ) {
                    state.error?.let {
                        ErrorDialog(
                            onDismiss = screenModel::resetError,
                            text = it
                        )
                    }
                    ScheduleDaySelector(
                        currentPage = currentPage,
                        daysOfWeek = daysOfWeek,
                        onDaySelected = { screenModel.selectDay(it) },
                        indicatorColor = AppTheme.colors.black
                    )
                    HorizontalPager(state = pagerState) { page ->
                        val day = page + 1
                        ScheduleLessonList(
                            lessons = state.lessonsList.filter { it.dayOfWeek == day }
                                .sortedBy { it.scheduleInfo.lessonNumber },
                            onLessonClick = { lesson ->
                                screenModel.selectLesson(lesson)
                                navigator.push(AttendanceScreen(lesson))
                            }
                        )
                    }
                }
            }
        }
    }
}