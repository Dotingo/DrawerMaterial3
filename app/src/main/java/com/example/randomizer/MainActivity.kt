package com.example.randomizer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.example.randomizer.data.AppTheme
import com.example.randomizer.ui.theme.RandomizerTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setContent {
            var appTheme by remember { mutableStateOf(AppTheme.System) }
            RandomizerTheme(
                isDarkTheme = when(appTheme){
                    AppTheme.Light -> false
                    AppTheme.Dark -> true
                    AppTheme.System -> isSystemInDarkTheme()
                }
            ) {
                Drawer()
            }
        }
    }
}


