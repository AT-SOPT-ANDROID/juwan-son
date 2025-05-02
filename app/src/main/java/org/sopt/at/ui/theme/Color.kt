package org.sopt.at.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)


val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)


var Purple25 = Color(0xFFFAFAFF)
val Purple50 = Color(0xFFF2F2FF)
val Purple100 = Color(0xFFDCDDFF)
val Purple200 = Color(0xFFC5C6FD)
val Purple300 = Color(0xFF9899F9)


val Red25 =Color(0xFFD0312D)
val Red50 =Color(0xFFE3242B)
val Red100 =Color(0xFFD21404)
val Red200 =Color(0xFFB90E0A)


@Immutable
data class TivingColors(
    val Purple25: Color,
    val Purple50: Color,
    val Purple100: Color,
    val Purple200: Color,
    val Purple300: Color,

    val Red25: Color,
    val Red50 : Color,
    val Red100 : Color,
    val Red200 : Color
)

val defaultTivingColors = TivingColors(
    Purple25 = Purple25,
    Purple50 = Purple50,
    Purple100 = Purple100,
    Purple200 = Purple200,
    Purple300 = Purple300,
    Red25= Red25,
    Red50= Red50,
    Red100 = Red100,
    Red200 =Red200

)

val LocalTivingColorsProvider = staticCompositionLocalOf { defaultTivingColors }