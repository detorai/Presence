package org.example.presenceapp.ui.feature.commons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.presenceapp.composeapp.resources.Res
import com.presenceapp.composeapp.resources.arrow_back
import com.presenceapp.composeapp.resources.is_here
import com.presenceapp.composeapp.resources.isnt_here
import org.example.presenceapp.domain.entities.AttendanceType
import org.example.presenceapp.ui.feature.commons.types.ButtonType
import org.example.presenceapp.ui.feature.commons.types.ScreenType
import org.example.presenceapp.ui.theme.AppTheme
import org.jetbrains.compose.resources.painterResource

@Composable
fun CommonTopBar(
    screenType: ScreenType,
    text: String,
    onChangeSortType: ((AttendanceType) -> Unit)? = null
) {
    val navigator = LocalNavigator.currentOrThrow
    val selectedIconState = remember { mutableStateOf(false) }

    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(AppTheme.colors.white)
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
            .height(75.dp)
    ) {
        CommonIconButton(
            background = AppTheme.colors.black,
            icon = painterResource(Res.drawable.arrow_back),
            iconColor = AppTheme.colors.white,
            buttonType = ButtonType.notSWITCHABLE,
            onClick = { navigator.pop() },
            modifier = Modifier
        )
        CommonLabel(
            text = text,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .weight(1f)
                .padding(end = if (screenType == ScreenType.GROUP) 0.dp else 44.dp)
        )
        if (screenType == ScreenType.GROUP) {
            val newSortType = when (selectedIconState.value) {
                false -> AttendanceType.ABSENT
                true -> AttendanceType.PRESENT
            }

            CommonIconButton(
                background = AppTheme.colors.black,
                icon = painterResource(Res.drawable.isnt_here),
                switchedIcon = painterResource(Res.drawable.is_here),
                iconColor = AppTheme.colors.white,
                buttonType = ButtonType.SWITCHABLE,
                selectedIconState = selectedIconState,
                onClick = {
                    selectedIconState.value = !selectedIconState.value
                    onChangeSortType?.invoke(newSortType)
                },
                modifier = Modifier
            )
        }
    }
}