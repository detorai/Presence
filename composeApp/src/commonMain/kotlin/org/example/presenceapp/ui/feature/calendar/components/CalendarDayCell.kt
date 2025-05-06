package org.example.presenceapp.ui.feature.calendar.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.presenceapp.domain.entities.AttendanceType
import org.example.presenceapp.domain.entities.DayData
import org.example.presenceapp.ui.theme.AppTheme

@Composable
fun DayCell(
    day: DayData,
    isToday: Boolean,
    onClick: () -> Unit
) {
    val borderColor = when {
        isToday -> AppTheme.colors.black
        !day.isCurrentMonth -> Color.Transparent
        else -> when (day.attendance) {
            AttendanceType.PRESENT -> AppTheme.colors.gray
            AttendanceType.ABSENT -> AppTheme.colors.red
            null -> AppTheme.colors.gray
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .border(1.dp, borderColor, RoundedCornerShape(10.dp))
            .clickable(
                enabled = day.isCurrentMonth,
                onClick = onClick
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = day.date.dayOfMonth.toString(),
                color = when {
                    !day.isCurrentMonth -> AppTheme.colors.black.copy(alpha = 0.3f)
                    else -> AppTheme.colors.black
                },
                style = AppTheme.typography.message
            )
        }
    }
}