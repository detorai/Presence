package org.example.presenceapp.ui.feature.weeks.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.presenceapp.ui.theme.AppTheme

@Composable
fun ScheduleCard(
    text: String,
    onClick: ()-> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color.White, RoundedCornerShape(10.dp))
            .border(1.dp, shape = RoundedCornerShape(10.dp), color = Color(0xFFd9d9d9))
            .clickable{
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,

    ) {
        Text(
            text,
            color = AppTheme.colors.black,
            modifier = Modifier.padding(start = 17.19.dp),
            style = AppTheme.typography.message
        )
    }
}