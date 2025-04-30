package org.example.presenceapp.ui.schedule.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.presenceapp.domain.common.SampleData.lessonTimes
import org.example.presenceapp.domain.entities.Schedule
import org.example.presenceapp.ui.theme.AppTheme

@Composable
fun ScheduleLessonItem(
    lesson: Schedule,
    index: Int,
    onLessonClick: (Schedule) -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        border = BorderStroke(1.dp, AppTheme.colors.gray),
        modifier = Modifier
            .fillMaxWidth()
            .background(AppTheme.colors.white)
            .clickable { onLessonClick(lesson) }
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = "$index",
                color = AppTheme.colors.black,
                style = AppTheme.typography.name,
                modifier = Modifier
                    .padding(start = 5.dp, end = 21.dp)
                    .align(Alignment.CenterVertically)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(text = lesson.scheduleInfo.subject.name, color = AppTheme.colors.black, style = AppTheme.typography.name)
                Text(text = "Кабинет: ${lesson.scheduleInfo.audience}", color = AppTheme.colors.black, style = AppTheme.typography.data)
            }
            Text(
                text = lessonTimes.getOrNull(index - 1) ?: "",
                style = AppTheme.typography.data,
                color = AppTheme.colors.black,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
    }
}