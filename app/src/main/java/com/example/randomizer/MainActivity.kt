package com.example.randomizer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.randomizer.data.AppTheme
import com.example.randomizer.ui.theme.RandomizerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: MainViewModel = hiltViewModel()

            LaunchedEffect(Unit) {
                viewModel.getTheme(this@MainActivity)
            }

            RandomizerTheme(
                isDarkTheme = when (viewModel.appTheme.value) {
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


