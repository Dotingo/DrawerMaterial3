package com.dotingo.randomizer.presentation.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val ApplyIcon: ImageVector
    get() {
        if (_ApplyIcon != null) {
            return _ApplyIcon!!
        }
        _ApplyIcon = ImageVector.Builder(
            name = "Apply",
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
                moveTo(5f, 13f)
                lineTo(9f, 17f)
                lineTo(19f, 7f)
            }
        }.build()

        return _ApplyIcon!!
    }

@Suppress("ObjectPropertyName")
private var _ApplyIcon: ImageVector? = null
