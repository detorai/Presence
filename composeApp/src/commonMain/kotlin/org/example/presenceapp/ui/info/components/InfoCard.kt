package org.example.presenceapp.ui.info.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import org.example.presenceapp.ui.theme.AppTheme

@Composable
fun InfoCard(
    onClick: () -> Unit,
    text: String
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        border = BorderStroke(1.dp, AppTheme.colors.gray),
        modifier = Modifier
            .fillMaxWidth()
            .background(AppTheme.colors.white)
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = text,
                color = AppTheme.colors.black,
                style = AppTheme.typography.name,
                modifier = Modifier
                    .padding(start = 5.dp, end = 21.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}