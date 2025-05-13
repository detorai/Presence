package org.example.presenceapp.ui.feature.schedule

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus
import org.example.presenceapp.ui.base.SIDE_EFFECTS_KEY
import org.example.presenceapp.ui.feature.commons.CommonTopBar
import org.example.presenceapp.ui.feature.commons.types.ScreenType
import org.example.presenceapp.ui.feature.schedule.composables.ScheduleDaySelector
import org.example.presenceapp.ui.feature.schedule.composables.ScheduleLessonList
import org.example.presenceapp.ui.theme.AppTheme
import org.example.project.domain.models.Week
import org.example.project.domain.models.formatDay

data class ScheduleScreen(
    private val week: Week,
): Screen {
    @Composable
    override fun Content() {
        val viewModel = koinScreenModel<ScheduleScreenModel>()
        val state = viewModel.viewState.collectAsState().value
        val effect = viewModel.effect
        val daysOfWeek = listOf("Пн", "Вт", "Ср", "Чт", "Пт", "Сб")
        val pagerState = rememberPagerState(
            initialPage = state.currentDayIndex,
            pageCount = { daysOfWeek.size }
        )
        val currentPage = pagerState.currentPage + pagerState.currentPageOffsetFraction
        val currentDay = week.startDate.plus(currentPage.toInt(), DateTimeUnit.DAY)
        val snackbarHostState = remember { SnackbarHostState() }


        Schedule(  viewModel, state, daysOfWeek, pagerState, currentPage, currentDay, snackbarHostState, effect )

    }

    @Composable
    fun Schedule(
        screenModel: ScheduleScreenModel,
        state: ScheduleContract.State,
        daysOfWeek: List<String>,
        pagerState: PagerState,
        currentPage: Float,
        currentDay: LocalDate,
        snackbarHostState: SnackbarHostState,
        effectFlow: Flow<ScheduleContract.Effect>
    ) {
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
                }
            }
        }
        LaunchedEffect(pagerState) {
            screenModel.handlePagerState(pagerState)
        }

        LaunchedEffect(state.currentDayIndex) {
            if (!pagerState.isScrollInProgress) {
                pagerState.animateScrollToPage(state.currentDayIndex)
            }
        }
        Scaffold(
            topBar = {
                CommonTopBar(
                    screenType = ScreenType.SCHEDULE,
                    text = currentDay.formatDay()
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
                    currentPage = currentPage,
                    daysOfWeek = daysOfWeek,
                    onDaySelected = { screenModel.setEvent(ScheduleContract.Event.SelectDay(it)) },
                    indicatorColor = AppTheme.colors.black
                )
                HorizontalPager(state = pagerState) { page ->
                    val day = page + 1
                    ScheduleLessonList(
                        lessons = state.lessons[day]!!,
                        onLessonClick = { lesson ->
                            screenModel.setEvent(ScheduleContract.Event.SelectLesson(lesson))
                        }
                    )
                }
            }
        }
    }
}