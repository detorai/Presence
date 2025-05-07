package org.example.presenceapp.ui.feature.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import org.example.presenceapp.domain.entities.AttendanceType
import org.example.presenceapp.ui.feature.commons.CommonButton
import org.example.presenceapp.ui.feature.commons.CommonMediumText
import org.example.presenceapp.ui.feature.commons.CommonRegularText
import org.example.presenceapp.ui.theme.AppTheme

@Composable
fun Preset(
    onDismiss: () -> Unit,
    onStatusSelected: (AttendanceType) -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .background(AppTheme.colors.textField, RoundedCornerShape(16.dp))
                .wrapContentHeight()
                .padding(25.dp)
        ) {
            CommonMediumText(
                text = "Выберите статус по умолчанию",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            AttendanceType.entries.forEach { type ->
                CommonButton(
                    onClick = {
                        onStatusSelected(type)
                        onDismiss()
                    },
                    content = {
                        CommonRegularText(
                            text = when (type) {
                                AttendanceType.PRESENT -> "Присутствует"
                                AttendanceType.ABSENT -> "Отсутствует"
                            },
                            color = AppTheme.colors.white,
                            modifier = Modifier
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                )
            }
        }
    }
}