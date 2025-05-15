package org.example.presenceapp.ui.feature.commons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.presenceapp.composeapp.resources.Res
import com.presenceapp.composeapp.resources.info
import com.presenceapp.composeapp.resources.schedule
import com.presenceapp.composeapp.resources.settings
import org.example.presenceapp.ui.feature.info.InfoScreen
import org.example.presenceapp.ui.feature.settings.SettingsScreen
import org.example.presenceapp.ui.navigation.Screens
import org.example.presenceapp.ui.theme.AppTheme
import org.jetbrains.compose.resources.painterResource

@Composable
fun CommonBottomBar() {
    val navigator = LocalNavigator.currentOrThrow
    val currentScreen = navigator.lastItem
    val routes = listOf(
        Triple(Res.drawable.info, "Информация", InfoScreen()),
        Triple(Res.drawable.schedule, "Расписание", Screens.WeekScreen),
        Triple(Res.drawable.settings, "Настройки", SettingsScreen())
    )

    NavigationBar(
        containerColor = AppTheme.colors.black,
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            routes.forEach { (icon, label, screen) ->
                val isSelected = currentScreen?.key == screen.key
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .weight(1f)
                        .clickable {
                            if (currentScreen?.key != screen.key) {
                                navigator.push(screen)
                            }
                        }
                ) {
                    this@Row.NavigationBarItem(
                        selected = isSelected,
                        onClick = {
                            if (currentScreen?.key != screen.key) {
                                navigator.push(screen)
                            }
                        },
                        icon = {
                            Icon(
                                painter = painterResource(icon),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(24.dp)
                            )
                        },
                        colors = NavigationBarItemColors(
                            selectedIndicatorColor = AppTheme.colors.white,
                            selectedIconColor = AppTheme.colors.black,
                            selectedTextColor = AppTheme.colors.white,
                            unselectedIconColor = AppTheme.colors.white,
                            unselectedTextColor = AppTheme.colors.white,
                            disabledIconColor = AppTheme.colors.white,
                            disabledTextColor = AppTheme.colors.white,
                        )
                    )
                    Text(
                        text = label,
                        color = AppTheme.colors.white,
                        style = AppTheme.typography.bottomBar
                    )
                }
            }
        }
    }
}