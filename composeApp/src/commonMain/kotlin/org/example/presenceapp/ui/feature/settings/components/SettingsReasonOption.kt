package org.example.presenceapp.ui.feature.settings.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.example.presenceapp.ui.feature.commons.CommonLabel
import org.example.presenceapp.ui.theme.AppTheme
import org.example.presenceapp.ui.feature.commons.types.AbsenceType

@Composable
fun SettingsReasonOption(
    onReasonSelected: (AbsenceType) -> Unit
) {
    val reasons = listOf(
        AbsenceType.SICK,
        AbsenceType.COMPETITION
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.white)
    ) {
        CommonLabel(
            text = "Длительные причины отсутствия",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            items(reasons) { reason ->
                SettingsReasonItem(
                    absenceType = reason,
                    onClick = { onReasonSelected(reason) }
                )
            }
        }
    }
}

@Composable
fun SettingsReasonItem(
    absenceType: AbsenceType,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
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
                text = absenceType.reason,
                color = AppTheme.colors.black,
                style = AppTheme.typography.message,
                modifier = Modifier
                    .padding(start = 5.dp, end = 21.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}