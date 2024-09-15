package com.dotingo.randomizer.presentation.ui.icons.drawer_icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val NameIcon: ImageVector
    get() {
        if (_NameIcon != null) {
            return _NameIcon!!
        }
        _NameIcon = ImageVector.Builder(
            name = "Name",
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
                curveTo(15.314f, 21f, 18f, 19.209f, 18f, 17f)
                curveTo(18f, 14.791f, 15.314f, 13f, 12f, 13f)
                curveTo(8.686f, 13f, 6f, 14.791f, 6f, 17f)
                curveTo(6f, 19.209f, 8.686f, 21f, 12f, 21f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFF303037)),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(12f, 10f)
                curveTo(13.933f, 10f, 15.5f, 8.433f, 15.5f, 6.5f)
                curveTo(15.5f, 4.567f, 13.933f, 3f, 12f, 3f)
                curveTo(10.067f, 3f, 8.5f, 4.567f, 8.5f, 6.5f)
                curveTo(8.5f, 8.433f, 10.067f, 10f, 12f, 10f)
                close()
            }
        }.build()

        return _NameIcon!!
    }

@Suppress("ObjectPropertyName")
private var _NameIcon: ImageVector? = null
