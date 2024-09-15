package com.dotingo.randomizer.presentation.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val Dice6: ImageVector
    get() {
        if (_Dice6 != null) {
            return _Dice6!!
        }
        _Dice6 = ImageVector.Builder(
            name = "Dice6",
            defaultWidth = 550.dp,
            defaultHeight = 550.dp,
            viewportWidth = 550f,
            viewportHeight = 550f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF303037)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(445f, 105f)
                horizontalLineTo(105f)
                verticalLineTo(445f)
                horizontalLineTo(445f)
                verticalLineTo(105f)
                close()
                moveTo(58f, 58f)
                verticalLineTo(492f)
                horizontalLineTo(492f)
                verticalLineTo(58f)
                horizontalLineTo(58f)
                close()
            }
        }.build()

        return _Dice6!!
    }

@Suppress("ObjectPropertyName")
private var _Dice6: ImageVector? = null
