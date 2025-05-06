package org.sopt.at.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.sopt.at.R


val tivingFontBold = FontFamily(Font(R.font.pretendard_bold))
val tivingFontSemiBold = FontFamily(Font(R.font.pretendard_semibold))
val tivingFontMedium = FontFamily(Font(R.font.pretendard_medium))
val tivingFontRegular = FontFamily(Font(R.font.pretendard_regular))

@Immutable
data class TivingTypography(
    val heading01_B: TextStyle,
    val heading01_SB: TextStyle,
    val heading01_R: TextStyle,
    val heading01_M:TextStyle

)

val defaultTivingTypography = TivingTypography(
    heading01_B = TextStyle(
        fontFamily = tivingFontBold,
        fontSize = 32.sp,
        fontWeight = FontWeight(700),
        lineHeight = 44.sp
    ),
    heading01_SB = TextStyle(
        fontFamily = tivingFontSemiBold,
        fontSize = 32.sp,
        fontWeight = FontWeight(700),
        lineHeight = 44.sp
    ),
    heading01_R = TextStyle(
        fontFamily = tivingFontRegular,
        fontSize = 32.sp,
        fontWeight = FontWeight(700),
        lineHeight = 44.sp
    ),
    heading01_M = TextStyle(
        fontFamily = tivingFontMedium,
        fontSize = 32.sp,
        fontWeight = FontWeight(700),
        lineHeight = 44.sp
    ),

)

val LocalTivingTypographyProvider = staticCompositionLocalOf { defaultTivingTypography }
// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )


    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)