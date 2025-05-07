package org.example.presenceapp.ui.feature.login.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.presenceapp.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginTopBar(
    top: Int
){
    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                Text(
                    "Добро пожаловать!",
                    style = AppTheme.typography.main,
                    color = AppTheme.colors.black,
                    modifier = Modifier.padding(top = 142.dp)
                )
            }
        },
        modifier = Modifier.padding(top = top.dp),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = AppTheme.colors.white
        )
    )
}