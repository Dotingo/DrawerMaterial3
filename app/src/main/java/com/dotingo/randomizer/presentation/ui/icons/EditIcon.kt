package com.dotingo.randomizer.presentation.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val EditIcon: ImageVector
    get() {
        if (_EditIcon != null) {
            return _EditIcon!!
        }
        _EditIcon = ImageVector.Builder(
            name = "Edit",
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
                moveTo(20.304f, 8.694f)
                lineTo(9.168f, 19.83f)
                curveTo(8.796f, 20.202f, 8.355f, 20.496f, 7.87f, 20.697f)
                curveTo(7.384f, 20.897f, 6.864f, 21f, 6.339f, 21f)
                horizontalLineTo(4f)
                curveTo(3.735f, 21f, 3.48f, 20.895f, 3.293f, 20.707f)
                curveTo(3.105f, 20.52f, 3f, 20.265f, 3f, 20f)
                verticalLineTo(17.661f)
                curveTo(3f, 17.136f, 3.103f, 16.616f, 3.303f, 16.13f)
                curveTo(3.504f, 15.645f, 3.798f, 15.204f, 4.17f, 14.832f)
                lineTo(15.306f, 3.696f)
                curveTo(15.755f, 3.25f, 16.362f, 3f, 16.995f, 3f)
                curveTo(17.628f, 3f, 18.236f, 3.25f, 18.685f, 3.696f)
                lineTo(20.304f, 5.315f)
                curveTo(20.75f, 5.765f, 21f, 6.372f, 21f, 7.005f)
                curveTo(21f, 7.637f, 20.75f, 8.245f, 20.304f, 8.694f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFF303037)),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(13.5f, 5.506f)
                lineTo(18.5f, 10.506f)
            }
        }.build()

        return _EditIcon!!
    }

@Suppress("ObjectPropertyName")
private var _EditIcon: ImageVector? = null
