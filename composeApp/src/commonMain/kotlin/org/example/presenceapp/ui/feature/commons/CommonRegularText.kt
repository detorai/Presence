package org.example.presenceapp.ui.feature.commons

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import org.example.presenceapp.ui.theme.AppTheme

@Composable
fun CommonRegularText(
    text: String,
    color: Color,
    textAlign: TextAlign? = null,
    modifier: Modifier
) {
    Text(
        text = text,
        color = color,
        style = AppTheme.typography.message,
        textAlign = textAlign,
        modifier = modifier
    )
}