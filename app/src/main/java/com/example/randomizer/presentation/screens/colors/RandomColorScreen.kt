package com.example.randomizer.presentation.screens.colors


import android.widget.Space
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.randomizer.presentation.util.Dimens.SmallPadding
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt
import kotlin.random.Random

@Composable
fun RandomColorScreen(paddingValues: PaddingValues) {

    var color by remember { mutableStateOf(Color.Unspecified) }
    val animatedColor by animateColorAsState(
        targetValue = color,
        animationSpec = tween(1000),
        label = "change colors"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = SmallPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
                    .background(color = animatedColor)
            )
            if (color != Color.Unspecified) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = color.toHex(), fontSize = 20.sp)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = color.toRgb(), fontSize = 20.sp)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = color.toHsl(), fontSize = 20.sp)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = color.toHsv(), fontSize = 20.sp)
                }

            }
        }
        Button(onClick = {
            color = generateRandomColor()
        }) {
            Text(text = "Сгенерировать цвета")
        }
    }
}

fun generateRandomColor(): Color {
    return Color(
        red = Random.nextFloat(),
        green = Random.nextFloat(),
        blue = Random.nextFloat()
    )
}

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
    // Извлекаем RGB компоненты и нормализуем их в диапазон [0, 1]
    val r = this.red
    val g = this.green
    val b = this.blue

    val max = max(r, max(g, b))
    val min = min(r, min(g, b))
    val delta = max - min

    // Вычисляем Value (V)
    val v = max

    // Вычисляем Saturation (S)
    val s = if (max == 0f) {
        0f
    } else {
        delta / max
    }

    // Вычисляем Hue (H)
    val h = when {
        delta == 0f -> 0f
        max == r -> ((g - b) / delta) % 6f
        max == g -> ((b - r) / delta) + 2f
        else -> ((r - g) / delta) + 4f
    } * 60f

    val hue = if (h < 0) h + 360f else h
    val saturation = s * 100
    val value = v * 100

    // Формируем строку HSV(h, s%, v%)
    return "HSV(${hue.roundToInt()}°, ${saturation.roundToInt()}%, ${value.roundToInt()}%)"
}