package com.dotingo.randomizer.presentation.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val OutlinedTrashIcon: ImageVector
    get() {
        if (_OutlinedTrashIcon != null) {
            return _OutlinedTrashIcon!!
        }
        _OutlinedTrashIcon = ImageVector.Builder(
            name = "Trash",
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
                moveTo(18f, 10f)
                lineTo(17.33f, 17.36f)
                curveTo(17.24f, 18.357f, 16.779f, 19.285f, 16.038f, 19.958f)
                curveTo(15.298f, 20.632f, 14.331f, 21.004f, 13.33f, 21f)
                horizontalLineTo(10.63f)
                curveTo(9.629f, 21.004f, 8.662f, 20.632f, 7.922f, 19.958f)
                curveTo(7.181f, 19.285f, 6.72f, 18.357f, 6.63f, 17.36f)
                lineTo(6f, 10f)
            }
            path(
                stroke = SolidColor(Color(0xFF303037)),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(20f, 7f)
                horizontalLineTo(4f)
            }
            path(
                stroke = SolidColor(Color(0xFF303037)),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(16.5f, 7f)
                horizontalLineTo(7.5f)
                lineTo(8f, 4.89f)
                curveTo(8.134f, 4.344f, 8.449f, 3.859f, 8.894f, 3.516f)
                curveTo(9.339f, 3.172f, 9.888f, 2.991f, 10.45f, 3f)
                horizontalLineTo(13.55f)
                curveTo(14.112f, 2.991f, 14.661f, 3.172f, 15.106f, 3.516f)
                curveTo(15.551f, 3.859f, 15.866f, 4.344f, 16f, 4.89f)
                lineTo(16.5f, 7f)
                close()
            }
        }.build()

        return _OutlinedTrashIcon!!
    }

@Suppress("ObjectPropertyName")
private var _OutlinedTrashIcon: ImageVector? = null
