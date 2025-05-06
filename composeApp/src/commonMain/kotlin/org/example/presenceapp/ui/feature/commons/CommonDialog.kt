package org.example.presenceapp.ui.feature.commons

import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.DialogProperties
import org.example.presenceapp.ui.theme.AppTheme

@Composable
fun CommonDialog(
    expanded: Boolean,
    onDismiss: () -> Unit,
    content: @Composable () -> Unit
) {
    if (expanded) {
        AlertDialog(
            containerColor = AppTheme.colors.textField,
            onDismissRequest = { onDismiss() },
            properties = DialogProperties(dismissOnClickOutside = true),
            confirmButton = {},
            text = content
        )
    }
}