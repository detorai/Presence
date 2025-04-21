package org.example.presenceapp.ui.schedule.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.presenceapp.domain.entities.Schedule
import org.example.presenceapp.ui.theme.AppTheme

@Composable
fun ScheduleLessonList(lessons: List<Schedule>,
                       onLessonClick: (Schedule) -> Unit) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.white)
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        if (lessons.isEmpty()) {
            item {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Нет занятий")
                }
            }
        } else {
            items(8) { index ->
                val lessonNumber = index + 1
                val lesson = lessons.firstOrNull{it.lessonNumber == lessonNumber}
                if (lesson !=  null) {
                    ScheduleLessonItem(
                        lesson = lesson,
                        index = lessonNumber,
                        onLessonClick = onLessonClick
                    )
                } else  {
                    EmptyScheduleLessonsItem(
                        lessonNumber
                    )
                }
            }
        }
    }
}