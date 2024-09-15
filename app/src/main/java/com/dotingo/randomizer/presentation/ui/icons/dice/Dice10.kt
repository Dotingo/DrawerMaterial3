package com.dotingo.randomizer.presentation.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val Dice10: ImageVector
    get() {
        if (_Dice10 != null) {
            return _Dice10!!
        }
        _Dice10 = ImageVector.Builder(
            name = "Dice10",
            defaultWidth = 639.dp,
            defaultHeight = 639.dp,
            viewportWidth = 639f,
            viewportHeight = 639f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(21.2f, 261.93f)
                lineTo(319.49f, 0.61f)
                lineTo(618.03f, 261.93f)
                lineTo(638.74f, 329.61f)
                lineTo(592.99f, 401.11f)
                lineTo(319.49f, 638.11f)
                lineTo(45.99f, 401.11f)
                lineTo(45.99f, 401.11f)
                lineTo(0.49f, 329.61f)
                lineTo(21.2f, 261.93f)
                close()
                moveTo(187.64f, 149.35f)
                lineTo(46.72f, 355.7f)
                lineTo(27.76f, 325.91f)
                lineTo(43.02f, 276.05f)
                lineTo(187.64f, 149.35f)
                close()
                moveTo(319.49f, 44.94f)
                lineTo(82f, 392.71f)
                lineTo(319.49f, 526.59f)
                lineTo(556.98f, 392.71f)
                lineTo(319.49f, 44.94f)
                close()
                moveTo(592.31f, 355.79f)
                lineTo(451.15f, 149.08f)
                lineTo(596.2f, 276.05f)
                lineTo(611.45f, 325.87f)
                lineTo(592.31f, 355.79f)
                close()
                moveTo(389.44f, 544.41f)
                lineTo(344.49f, 583.37f)
                lineTo(344.49f, 569.73f)
                lineTo(389.44f, 544.41f)
                close()
                moveTo(294.58f, 583.45f)
                lineTo(249.33f, 544.23f)
                lineTo(294.6f, 569.72f)
                lineTo(294.58f, 583.45f)
                close()
            }
        }.build()

        return _Dice10!!
    }

@Suppress("ObjectPropertyName")
private var _Dice10: ImageVector? = null
