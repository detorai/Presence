package org.example.presenceapp.ui.commons

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.example.presenceapp.ui.theme.AppTheme

@Composable
fun CommonButton(
    onClick: () -> Unit,
    content: @Composable () -> Unit,
    modifier: Modifier
) {
    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = AppTheme.colors.black,
            contentColor = AppTheme.colors.white
        ),
        onClick = onClick,
        modifier = modifier
    ) {
        content()
    }
}