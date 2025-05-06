package org.example.presenceapp.ui.feature.login.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CheckBoxRow(
    check: Boolean,
    onCheck: (Boolean) -> Unit,
    top: Int
){
    Row(
        modifier = Modifier.padding(top = top.dp).fillMaxWidth().height(44.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = check,
            onCheckedChange = onCheck,
            modifier = Modifier.size(18.dp),
            colors = CheckboxDefaults.colors(
                checkedColor = Color(0xFF2c2c2c),
                checkmarkColor = Color.White   ,
                uncheckedColor = Color(0xFFd9d9d9)
            )
        )
        Text(
            "Пользовательское\nсоглашение",
            textAlign = TextAlign.Start,
            color = Color(0xFF2c2c2c),
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}