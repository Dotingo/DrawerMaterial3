package com.example.randomizer.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable


/**
 * Map a AppTheme into a [Boolean].
 *
 * @return the corresponding boolean value of this AppTheme.
 */
enum class AppTheme {
    Light, Dark, System
}

@Composable
fun shouldUseDarkTheme(
    appTheme: AppTheme
): Boolean = when(appTheme) {
    AppTheme.System -> isSystemInDarkTheme()
    AppTheme.Light -> false
    AppTheme.Dark -> true
}