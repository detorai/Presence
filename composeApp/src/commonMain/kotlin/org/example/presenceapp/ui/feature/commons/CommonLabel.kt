package org.example.presenceapp.ui.feature.commons

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import org.example.presenceapp.ui.theme.AppTheme

@Composable
fun CommonLabel(
    text: String,
    textAlign: TextAlign? = null,
    modifier: Modifier
) {
    Text(
        text = text,
        color = AppTheme.colors.black,
        style = AppTheme.typography.name,
        textAlign = textAlign,
        modifier = modifier
    )
}