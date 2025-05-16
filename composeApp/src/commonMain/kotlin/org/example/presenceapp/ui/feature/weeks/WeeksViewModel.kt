package org.example.project.ui.weeks

import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.todayIn
import org.example.presenceapp.ui.base.BaseViewModel
import org.example.project.domain.models.MonthWithWeeks
import org.example.project.domain.models.Week

class WeeksViewModel:
    BaseViewModel<WeekContract.Event, WeekContract.State, WeekContract.Effect>() {

    init {
        loadWeeks()
    }

    override fun setInitialState() = WeekContract.State()

    override fun handleEvents(event: WeekContract.Event) {
        when (event) {
            is WeekContract.Event.WeekSelected -> {
                setEffect {
                    WeekContract.Effect.Navigation.ToSchedule(event.week)
                }
            }
        }
    }

    private fun loadWeeks() {
        screenModelScope.launch {
            try {
                val weeks = getWeeksInCurrentMonth().map { (start, end) ->
                    Week(
                        startDate = start,
                        endDate = end
                    )
                }
                val currentMonth = Clock.System.todayIn(TimeZone.currentSystemDefault()).month
                val currentYear = Clock.System.todayIn(TimeZone.currentSystemDefault()).year

                setState {
                    copy(
                        data = listOf(
                            MonthWithWeeks(
                                month = currentMonth,
                                year = currentYear,
                                weeks = weeks
                            )
                        ),
                        success = true
                    )
                }
            } catch (e: Exception) {
                setEffect { WeekContract.Effect.ShowError(e.message) }
            }
        }
    }


    private fun getWeeksInCurrentMonth(): List<Pair<LocalDate, LocalDate>> {
        val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
        val firstDayOfMonth = LocalDate(today.year, today.month, 1)
        val lastDayOfMonth = firstDayOfMonth.plus(1, DateTimeUnit.MONTH).minus(1, DateTimeUnit.DAY)

        val weeks = mutableListOf<Pair<LocalDate, LocalDate>>()
        var currentWeekStart = firstDayOfMonth.findPreviousMonday()

        while (currentWeekStart <= lastDayOfMonth || currentWeekStart.plus(6, DateTimeUnit.DAY) <= lastDayOfMonth) {
            val weekEnd = currentWeekStart.plus(6, DateTimeUnit.DAY)

            if (currentWeekStart <= lastDayOfMonth || weekEnd >= firstDayOfMonth) {
                weeks.add(currentWeekStart to weekEnd)
            }

            currentWeekStart = currentWeekStart.plus(7, DateTimeUnit.DAY)
        }

        return weeks.filter { (start, end) ->
            start <= lastDayOfMonth || end >= firstDayOfMonth
        }
    }

    private fun LocalDate.findPreviousMonday(): LocalDate {
        var date = this
        while (date.dayOfWeek != DayOfWeek.MONDAY) {
            date = date.minus(1, DateTimeUnit.DAY)
        }
        return date
    }
}