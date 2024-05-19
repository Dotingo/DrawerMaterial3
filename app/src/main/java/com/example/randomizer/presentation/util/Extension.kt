package com.example.randomizer.presentation.util

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import com.example.randomizer.data.AppTheme


/**
 * Map a AppTheme into a [Boolean].
 *
 * @param appTheme the [AppTheme].
 *
 * @return the corresponding boolean value of this AppTheme.
 */

@Composable
fun shouldUseDarkTheme(
    appTheme: AppTheme
): Boolean = when(appTheme) {
    AppTheme.System -> isSystemInDarkTheme()
    AppTheme.Light -> false
    AppTheme.Dark -> true
}