package com.dotingo.randomizer.presentation.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val InfoIcon: ImageVector
    get() {
        if (_Info != null) {
            return _Info!!
        }
        _Info = ImageVector.Builder(
            name = "Info",
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
                moveTo(12f, 21f)
                curveTo(16.95f, 21f, 21f, 16.95f, 21f, 12f)
                curveTo(21f, 7.05f, 16.95f, 3f, 12f, 3f)
                curveTo(7.05f, 3f, 3f, 7.05f, 3f, 12f)
                curveTo(3f, 16.95f, 7.05f, 21f, 12f, 21f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFF303037)),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(12f, 8.5f)
                lineTo(12f, 12f)
            }
            path(
                stroke = SolidColor(Color(0xFF303037)),
                strokeLineWidth = 2.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(11.995f, 15.5f)
                horizontalLineTo(12.004f)
            }
        }.build()

        return _Info!!
    }

@Suppress("ObjectPropertyName")
private var _Info: ImageVector? = null
