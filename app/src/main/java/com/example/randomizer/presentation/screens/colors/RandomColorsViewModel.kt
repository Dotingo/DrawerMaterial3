package com.example.randomizer.presentation.screens.colors

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.randomizer.presentation.components.toCmyk
import com.example.randomizer.presentation.components.toHex
import com.example.randomizer.presentation.components.toHsl
import com.example.randomizer.presentation.components.toHsv
import com.example.randomizer.presentation.components.toRgb
import kotlin.random.Random

class RandomColorsViewModel: ViewModel() {

    fun generateRandomColor(): Color {
        return Color(
            red = Random.nextFloat(),
            green = Random.nextFloat(),
            blue = Random.nextFloat()
        )
    }

    fun getColors(colorType : String, color: Color): String {
        return when(colorType){
            "HEX" -> {color.toHex()}
            "RGB" -> {color.toRgb()}
            "HSL" -> {color.toHsl()}
            "HSV" -> {color.toHsv()}
            "CMYK" -> {color.toCmyk()}
            else -> ""
        }
    }
}