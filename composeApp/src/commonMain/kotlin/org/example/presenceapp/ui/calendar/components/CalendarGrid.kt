package org.example.presenceapp.ui.calendar.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import kotlinx.datetime.LocalDate
import org.example.presenceapp.domain.entities.DayData

@Composable
fun CalendarGrid(
    monthData: List<DayData>,
    currentDate: LocalDate,
    onDayClick: (LocalDate) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(monthData) { day ->
            DayCell(
                day = day,
                isToday = day.date == currentDate,
                onClick = { onDayClick(day.date) }
            )
        }
    }
}