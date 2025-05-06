package org.example.presenceapp.ui.feature.login.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.presenceapp.ui.theme.AppTheme

@Composable
fun PresenceButton(
    text: String,
    onClick: () -> Unit,
    bottom: Int
){
    Button(
        modifier = Modifier.padding(bottom = bottom.dp).fillMaxWidth(),
        onClick = { onClick() },
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = AppTheme.colors.black,
            contentColor = AppTheme.colors.textField,
            disabledContentColor = AppTheme.colors.gray,
            disabledContainerColor =AppTheme.colors.textField
        )
    ){
        Text(
            text,
            style = AppTheme.typography.message
        )
    }
}