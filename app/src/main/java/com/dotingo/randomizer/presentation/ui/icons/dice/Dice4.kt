package com.dotingo.randomizer.presentation.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val Dice4: ImageVector
    get() {
        if (_Dice4 != null) {
            return _Dice4!!
        }
        _Dice4 = ImageVector.Builder(
            name = "Dice4",
            defaultWidth = 550.dp,
            defaultHeight = 586.dp,
            viewportWidth = 550f,
            viewportHeight = 586f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFF303037)),
                strokeLineWidth = 49f
            ) {
                moveTo(42.74f, 445f)
                lineTo(274.96f, 48.48f)
                lineTo(507.18f, 445f)
                horizontalLineTo(42.74f)
                close()
            }
        }.build()

        return _Dice4!!
    }

@Suppress("ObjectPropertyName")
private var _Dice4: ImageVector? = null
