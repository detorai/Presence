package org.example.presenceapp.ui.feature.schedule.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.lerp
import org.example.presenceapp.ui.theme.AppTheme
import kotlin.math.abs

@Composable
fun ScheduleDayText(
    index: Int,
    currentPage: Float,
    daysOfWeek: List<String>,
    onDaySelected: (Int) -> Unit
) {
    val day = daysOfWeek[index]
    val progress by animateFloatAsState(
        targetValue = 1f - minOf(
            abs(currentPage - index),
            1f
        ).coerceIn(0f, 1f),
    )
    val textColor by animateColorAsState(
        targetValue = lerp(
            AppTheme.colors.black,
            AppTheme.colors.white,
            progress
        )
    )

    Text(
        text = day,
        color = textColor,
        style = AppTheme.typography.message,
        modifier = Modifier
            .clickable { onDaySelected(index) }
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
}