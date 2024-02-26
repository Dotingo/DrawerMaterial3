package com.example.randomizer.ui

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.randomizer.SettingsViewModel
import com.example.randomizer.navigate.TopNavHost
import com.example.randomizer.ui.theme.RandomizerTheme
import com.example.randomizer.util.shouldUseDarkTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val viewModel: SettingsViewModel = hiltViewModel()
            val activityState by viewModel.appTheme.collectAsStateWithLifecycle()
            LaunchedEffect(Unit) {
                viewModel.getTheme(this@MainActivity)
            }
            RandomizerTheme(
                isDarkTheme = shouldUseDarkTheme(appTheme = activityState)
            ) {
                TopNavHost()
            }
        }
    }
}


