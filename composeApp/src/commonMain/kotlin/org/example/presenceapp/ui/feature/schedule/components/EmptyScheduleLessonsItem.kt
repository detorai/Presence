package org.example.presenceapp.ui.feature.schedule.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import org.example.presenceapp.ui.theme.AppTheme

@Composable
fun EmptyScheduleLessonsItem(
    lessonNumber: Int
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        border = BorderStroke(1.dp, AppTheme.colors.gray),
        modifier = Modifier
            .fillMaxWidth()
            .background(AppTheme.colors.white)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = "$lessonNumber",
                color = AppTheme.colors.black.copy(alpha = 0.5f),
                style = AppTheme.typography.name,
                modifier = Modifier
                    .padding(start = 5.dp, end = 21.dp)
                    .align(Alignment.CenterVertically)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "-",
                    color = AppTheme.colors.black.copy(alpha = 0.5f),
                    style = AppTheme.typography.name
                )
                Text(
                    text = "Кабинет: —",
                    color = AppTheme.colors.black.copy(alpha = 0.5f),
                    style = AppTheme.typography.data
                )
            }
            Text(
                text = lessonTimes.getOrNull(lessonNumber - 1) ?: "",
                style = AppTheme.typography.data,
                color = AppTheme.colors.black.copy(alpha = 0.5f),
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}