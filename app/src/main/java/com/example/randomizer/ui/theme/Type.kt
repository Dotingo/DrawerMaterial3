package com.example.randomizer.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.randomizer.R

private val rubikFontFamily = FontFamily(
    fonts = listOf(
        Font(
            resId = R.font.rubik_black,
            weight = FontWeight.W900
        ),
        Font(
            resId = R.font.rubik_light,
            weight = FontWeight.W300
        ),
        Font(
            resId = R.font.rubik_regular,
            weight = FontWeight.W400
        ),
        Font(
            resId = R.font.rubik_medium,
            weight = FontWeight.W500
        ),
        Font(
            resId = R.font.rubik_bold,
            weight = FontWeight.W700
        ),
        Font(
            resId = R.font.rubik_semi_bold,
            weight = FontWeight.W600
        ),
        Font(
            resId = R.font.rubik_extra_bold,
            weight = FontWeight.W800
        )
    )
)

val Typography = Typography(
    displaySmall = TextStyle(
        fontFamily = rubikFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 27.sp,
        lineHeight = 28.sp
    ),
    displayMedium = TextStyle(
        fontFamily = rubikFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
        lineHeight = 28.sp
    ),
    displayLarge = TextStyle(
        fontFamily = rubikFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 29.sp,
        lineHeight = 28.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = rubikFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 23.sp,
        lineHeight = 28.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = rubikFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 28.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = rubikFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 26.sp,
        lineHeight = 28.sp
    ),
    titleSmall = TextStyle(
        fontFamily = rubikFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 28.sp
    ),
    titleMedium = TextStyle(
        fontFamily = rubikFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        lineHeight = 28.sp
    ),
    titleLarge = TextStyle(
        fontFamily = rubikFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp
    ),
    bodySmall = TextStyle(
        fontFamily = rubikFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 28.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = rubikFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 28.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = rubikFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 25.sp
    ),
    labelSmall = TextStyle(
        fontFamily = rubikFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.2.sp
    ),
    labelMedium = TextStyle(
        fontFamily = rubikFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 13.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.2.sp
    ),
    labelLarge = TextStyle(
        fontFamily = rubikFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.2.sp
    ),
)

