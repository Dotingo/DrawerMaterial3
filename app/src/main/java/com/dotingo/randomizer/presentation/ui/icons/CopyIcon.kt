package com.dotingo.randomizer.presentation.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val CopyIcon: ImageVector
    get() {
        if (_CopyIcon != null) {
            return _CopyIcon!!
        }
        _CopyIcon = ImageVector.Builder(
            name = "Copy",
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
                moveTo(17f, 8f)
                horizontalLineTo(12f)
                curveTo(9.791f, 8f, 8f, 9.791f, 8f, 12f)
                verticalLineTo(17f)
                curveTo(8f, 19.209f, 9.791f, 21f, 12f, 21f)
                horizontalLineTo(17f)
                curveTo(19.209f, 21f, 21f, 19.209f, 21f, 17f)
                verticalLineTo(12f)
                curveTo(21f, 9.791f, 19.209f, 8f, 17f, 8f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFF303037)),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(16f, 8f)
                verticalLineTo(7f)
                curveTo(16f, 5.939f, 15.579f, 4.922f, 14.828f, 4.172f)
                curveTo(14.078f, 3.421f, 13.061f, 3f, 12f, 3f)
                horizontalLineTo(7f)
                curveTo(5.939f, 3f, 4.922f, 3.421f, 4.172f, 4.172f)
                curveTo(3.421f, 4.922f, 3f, 5.939f, 3f, 7f)
                verticalLineTo(12f)
                curveTo(3f, 13.061f, 3.421f, 14.078f, 4.172f, 14.828f)
                curveTo(4.922f, 15.579f, 5.939f, 16f, 7f, 16f)
                horizontalLineTo(8f)
            }
        }.build()

        return _CopyIcon!!
    }

@Suppress("ObjectPropertyName")
private var _CopyIcon: ImageVector? = null
