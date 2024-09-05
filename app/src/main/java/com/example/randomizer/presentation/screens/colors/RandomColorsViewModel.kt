package com.example.randomizer.presentation.screens.colors

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.randomizer.presentation.components.toCmyk
import com.example.randomizer.presentation.components.toHex
import com.example.randomizer.presentation.components.toHsl
import com.example.randomizer.presentation.components.toHsv
import com.example.randomizer.presentation.components.toRgb
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.random.Random

class RandomColorsViewModel : ViewModel() {
    private val _generatedColor = MutableStateFlow(Color.Unspecified)
    val generatedColor: StateFlow<Color> = _generatedColor.asStateFlow()

    private val _colorText = MutableStateFlow("")
    val colorText: StateFlow<String> = _colorText.asStateFlow()

    fun generateRandomColor() {
        _generatedColor.value = Color(
            red = Random.nextFloat(),
            green = Random.nextFloat(),
            blue = Random.nextFloat()
        )
        updateColorText(_colorText.value)
    }

    fun updateColorText(colorType: String) {
        _colorText.value = when (colorType) {
            "HEX" -> { generatedColor.value.toHex() }
            "RGB" -> { generatedColor.value.toRgb() }
            "HSL" -> { generatedColor.value.toHsl() }
            "HSV" -> { generatedColor.value.toHsv() }
            "CMYK" -> { generatedColor.value.toCmyk() }
            else -> ""
        }
    }
}