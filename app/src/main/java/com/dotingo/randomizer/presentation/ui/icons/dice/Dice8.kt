package com.dotingo.randomizer.presentation.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val Dice8: ImageVector
    get() {
        if (_Dice8 != null) {
            return _Dice8!!
        }
        _Dice8 = ImageVector.Builder(
            name = "Dice8",
            defaultWidth = 585.dp,
            defaultHeight = 803.dp,
            viewportWidth = 585f,
            viewportHeight = 803f
        ).apply {
            group {
                path(
                    fill = SolidColor(Color(0xFF303037)),
                    pathFillType = PathFillType.EvenOdd
                ) {
                    moveTo(0f, 422f)
                    lineTo(18.94f, 540.44f)
                    lineTo(303.99f, 803f)
                    lineTo(568.94f, 540.44f)
                    lineTo(585.44f, 422f)
                    lineTo(294.15f, 71.25f)
                    lineTo(294f, 71f)
                    lineTo(294f, 71f)
                    lineTo(293.97f, 71.04f)
                    lineTo(293.94f, 71f)
                    lineTo(293.96f, 71.04f)
                    lineTo(0f, 422f)
                    close()
                    moveTo(62.65f, 515.5f)
                    lineTo(294f, 120.47f)
                    lineTo(525.35f, 515.5f)
                    horizontalLineTo(62.65f)
                    close()
                    moveTo(108.69f, 575.5f)
                    lineTo(303.11f, 754.6f)
                    lineTo(483.9f, 575.5f)
                    horizontalLineTo(108.69f)
                    close()
                    moveTo(26.49f, 429.31f)
                    lineTo(125.1f, 311.58f)
                    lineTo(66.97f, 411.3f)
                    lineTo(32.79f, 468.59f)
                    lineTo(26.49f, 429.31f)
                    close()
                    moveTo(459.11f, 309.02f)
                    lineTo(559.16f, 429.48f)
                    lineTo(553.94f, 466.95f)
                    lineTo(459.11f, 309.02f)
                    close()
                }
            }
        }.build()

        return _Dice8!!
    }

@Suppress("ObjectPropertyName")
private var _Dice8: ImageVector? = null
