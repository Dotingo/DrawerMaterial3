package com.example.randomizer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.randomizer.ui.theme.RandomizerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RandomizerTheme {
                Drawer()
            }
        }
    }
}


