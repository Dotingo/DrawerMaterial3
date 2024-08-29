package com.example.randomizer.presentation.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.randomizer.navigation.TopNavHost
import com.example.randomizer.presentation.ui.theme.RandomizerTheme
import com.example.randomizer.presentation.ui.theme.shouldUseDarkTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val viewModel: MainViewModel by viewModels()
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { !viewModel.isThemeLoaded.value }

        setContent {
            val activityState by viewModel.appTheme.collectAsStateWithLifecycle()
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center){
                RandomizerTheme(
                    isDarkTheme = shouldUseDarkTheme(appTheme = activityState)
                ) {
                    TopNavHost(viewModel = viewModel)
                }
            }
        }
        lifecycleScope.launch {
            viewModel.getTheme(this@MainActivity)
        }
    }
}