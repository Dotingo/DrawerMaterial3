package com.example.randomizer.presentation.components

import androidx.compose.ui.graphics.Color
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt

fun Color.toHex(): String {
    val red = (this.red * 255).roundToInt()
    val green = (this.green * 255).roundToInt()
    val blue = (this.blue * 255).roundToInt()

    return String.format("#%02X%02X%02X", red, green, blue)
}

fun Color.toRgb(): String {
    val red = (this.red * 255).roundToInt()
    val green = (this.green * 255).roundToInt()
    val blue = (this.blue * 255).roundToInt()

    return "RGB($red, $green, $blue)"
}

fun Color.toHsl(): String {
    val r = this.red
    val g = this.green
    val b = this.blue

    val max = max(r, max(g, b))
    val min = min(r, min(g, b))
    val delta = max - min

    // Lightness
    val l = (max + min) / 2

    // Saturation
    val s = if (delta == 0f) {
        0f
    } else {
        delta / (1f - abs(2f * l - 1f))
    }

    // Hue
    val h = when {
        delta == 0f -> 0f
        max == r -> ((g - b) / delta) % 6f
        max == g -> ((b - r) / delta) + 2f
        else -> ((r - g) / delta) + 4f
    } * 60f

    val hue = if (h < 0) h + 360f else h
    val saturation = s * 100
    val lightness = l * 100

    return "HSL(${hue.roundToInt()}°, ${saturation.roundToInt()}%, ${lightness.roundToInt()}%)"
}

fun Color.toHsv(): String {
    val r = this.red
    val g = this.green
    val b = this.blue

    val max = max(r, max(g, b))
    val min = min(r, min(g, b))
    val delta = max - min

    // Value (V)
    val v = max

    // Saturation (S)
    val s = if (max == 0f) {
        0f
    } else {
        delta / max
    }

    // Hue (H)
    val h = when {
        delta == 0f -> 0f
        max == r -> ((g - b) / delta) % 6f
        max == g -> ((b - r) / delta) + 2f
        else -> ((r - g) / delta) + 4f
    } * 60f

    val hue = if (h < 0) h + 360f else h
    val saturation = s * 100
    val value = v * 100


    return "HSV(${hue.roundToInt()}°, ${saturation.roundToInt()}%, ${value.roundToInt()}%)"
}

fun Color.toCmyk(): String {
    val r = this.red
    val g = this.green
    val b = this.blue

    // K (Black)
    val k = 1f - max(r, max(g, b))

    // if K = 1, то color black
    val c = if (k == 1f) 0f else (1f - r - k) / (1f - k)
    val m = if (k == 1f) 0f else (1f - g - k) / (1f - k)
    val y = if (k == 1f) 0f else (1f - b - k) / (1f - k)

    val cyan = (c * 100).roundToInt()
    val magenta = (m * 100).roundToInt()
    val yellow = (y * 100).roundToInt()
    val black = (k * 100).roundToInt()

    return "CMYK($cyan%, $magenta%, $yellow%, $black%)"
}