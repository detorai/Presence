package org.example.presenceapp.ui.commons.types

enum class ScreenType {
    SCHEDULE,
    GROUP,
}

fun getScreenType(screenType: ScreenType): String {
    return when (screenType) {
        ScreenType.SCHEDULE -> "01.01"
        ScreenType.GROUP -> ""
    }
}