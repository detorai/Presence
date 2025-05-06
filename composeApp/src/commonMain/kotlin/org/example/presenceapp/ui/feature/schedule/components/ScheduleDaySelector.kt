package org.example.presenceapp.ui.feature.schedule.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import org.example.presenceapp.ui.theme.AppTheme

@Composable
fun ScheduleDaySelector(
    currentPage: Float,
    daysOfWeek: List<String>,
    onDaySelected: (Int) -> Unit,
    indicatorColor: Color
) {
    val density = LocalDensity.current
    var rowWidth by remember { mutableStateOf(0) }
    val itemWidthPx = if (rowWidth > 0) rowWidth.toFloat() / daysOfWeek.size else 0f

    Box(
        modifier = Modifier
            .background(AppTheme.colors.white)
            .padding(horizontal = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { rowWidth = it.size.width }
                .height(IntrinsicSize.Min)
        ) {
            if (itemWidthPx > 0) {
                Box(
                    modifier = Modifier
                        .offset { IntOffset((currentPage * itemWidthPx).toInt(), 0) }
                        .width(with(density) { itemWidthPx.toDp() })
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(8.dp))
                        .background(indicatorColor)
                )
            }

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                daysOfWeek.forEachIndexed { index, day ->
                    ScheduleDayText(
                        index = index,
                        currentPage = currentPage,
                        daysOfWeek = daysOfWeek,
                        onDaySelected = onDaySelected
                    )
                }
            }
        }
    }
}