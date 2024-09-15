package com.dotingo.randomizer.presentation.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val ClearIcon: ImageVector
    get() {
        if (_ClearIcon != null) {
            return _ClearIcon!!
        }
        _ClearIcon = ImageVector.Builder(
            name = "Clear",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFF303037)),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(5f, 5f)
                lineTo(19f, 19f)
            }
            path(
                stroke = SolidColor(Color(0xFF303037)),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(19f, 5f)
                lineTo(5f, 19f)
            }
        }.build()

        return _ClearIcon!!
    }

@Suppress("ObjectPropertyName")
private var _ClearIcon: ImageVector? = null
