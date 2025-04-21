package org.example.presenceapp.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.example.presenceapp.ui.theme.themeManager.ThemeState
import org.example.presenceapp.ui.theme.themeManager.ThemeTypes

val LocalAppTheme = compositionLocalOf { ThemeTypes.SYSTEM }

@Composable
expect fun rememberThemeState(): ThemeState

@Immutable
data class AppColors(
    val white: Color,
    val black: Color,
    val gray: Color,
    val textField: Color,
    val red: Color,
    val accent: Color,
    val accentText: Color,
    val mic: Color
) {
    companion object {
        fun light() = AppColors(
            white = Color(0xFFFFFFFF),
            black = Color(0xFF2C2C2C),
            gray = Color(0xFFD9D9D9),
            textField = Color(0xFFF6F6F6),
            red = Color(0xFFFF7F7F),
            accent = Color(0xFFD0E2EC),
            accentText = Color(0xFF7B99AB),
            mic = Color(0xFF01FFFF)
        )
        fun dark() = AppColors(
            white = Color(0xFF2C2C2C),
            black = Color(0xFFFFFFFF),
            gray = Color(0xFF828282),
            textField = Color(0xFF2D2D2D),
            red = Color(0xFFFF7F7F),
            accent = Color(0xFFD0E2EC),
            accentText = Color(0xFF7B99AB),
            mic = Color(0xFF01FFFF)
        )
    }
}

@Immutable
data class AppTextStyle(
    val main: TextStyle,
    val name: TextStyle,
    val messageFrag: TextStyle,
    val message: TextStyle,
    val data: TextStyle,
    val schedule: TextStyle,
    val bottomBar: TextStyle
)

val LocalAppColors = staticCompositionLocalOf {
    AppColors(
        white = Color.Unspecified,
        black = Color.Unspecified,
        gray = Color.Unspecified,
        textField = Color.Unspecified,
        red = Color.Unspecified,
        accent = Color.Unspecified,
        accentText = Color.Unspecified,
        mic = Color.Unspecified
    )
}

val LocalAppTypography = staticCompositionLocalOf {
    AppTextStyle(
        main = TextStyle.Default,
        name = TextStyle.Default,
        messageFrag = TextStyle.Default,
        message = TextStyle.Default,
        data = TextStyle.Default,
        schedule = TextStyle.Default,
        bottomBar = TextStyle.Default
    )
}

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    val themeState = rememberThemeState()

    val appColors = if (themeState.isDarkMode) { AppColors.dark() } else { AppColors.light() }

    val appTypography = AppTextStyle(
        main = TextStyle(fontWeight = FontWeight.Medium, fontSize = 26.sp),
        name = TextStyle(fontWeight = FontWeight.Medium, fontSize = 18.sp),
        messageFrag = TextStyle(fontWeight = FontWeight.Medium, fontSize = 16.sp),
        message = TextStyle(fontWeight = FontWeight.Normal, fontSize = 16.sp),
        data = TextStyle(fontWeight = FontWeight.Normal, fontSize = 14.sp),
        schedule = TextStyle(fontWeight = FontWeight.Normal, fontSize = 18.sp),
        bottomBar = TextStyle(fontWeight = FontWeight.Normal, fontSize = 12.sp)
    )

    CompositionLocalProvider(
        LocalAppTheme provides if (themeState.isDarkMode) ThemeTypes.DARK else ThemeTypes.LIGHT,
        LocalAppColors provides appColors,
        LocalAppTypography provides appTypography,
        content = content
    )
}

object AppTheme {
    val colors: AppColors
        @Composable
        get() = LocalAppColors.current

    val typography: AppTextStyle
        @Composable
        get() = LocalAppTypography.current
}