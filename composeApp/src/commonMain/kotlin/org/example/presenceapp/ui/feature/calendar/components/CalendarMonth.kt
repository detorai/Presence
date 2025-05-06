package org.example.presenceapp.ui.feature.calendar.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.datetime.Month

@Composable
fun MonthHeader(
    month: Month,
    year: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = "${month.name.lowercase().replaceFirstChar { it.titlecase() }} $year",
        style = MaterialTheme.typography.headlineMedium,
        modifier = modifier
    )
}