package com.dotingo.randomizer.presentation.ui.icons.list_navigate_icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val ShuffleIcon: ImageVector
    get() {
        if (_ShuffleIcon != null) {
            return _ShuffleIcon!!
        }
        _ShuffleIcon = ImageVector.Builder(
            name = "Shuffle",
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
                moveTo(18f, 9f)
                lineTo(21f, 6f)
                lineTo(18f, 3f)
            }
            path(
                stroke = SolidColor(Color(0xFF303037)),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(3f, 18f)
                horizontalLineTo(5.13f)
                curveTo(6.004f, 18f, 6.863f, 17.771f, 7.621f, 17.336f)
                curveTo(8.379f, 16.901f, 9.009f, 16.275f, 9.45f, 15.52f)
                lineTo(13.55f, 8.52f)
                curveTo(13.991f, 7.765f, 14.621f, 7.139f, 15.379f, 6.704f)
                curveTo(16.137f, 6.269f, 16.996f, 6.04f, 17.87f, 6.04f)
                horizontalLineTo(21f)
            }
            path(
                stroke = SolidColor(Color(0xFF303037)),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(18f, 21f)
                lineTo(21f, 18f)
                lineTo(18f, 15f)
            }
            path(
                stroke = SolidColor(Color(0xFF303037)),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(3f, 6f)
                horizontalLineTo(5.13f)
                curveTo(6.004f, 6f, 6.863f, 6.229f, 7.621f, 6.664f)
                curveTo(8.379f, 7.099f, 9.009f, 7.725f, 9.45f, 8.48f)
                lineTo(13.55f, 15.48f)
                curveTo(13.991f, 16.235f, 14.621f, 16.861f, 15.379f, 17.296f)
                curveTo(16.137f, 17.731f, 16.996f, 17.96f, 17.87f, 17.96f)
                horizontalLineTo(21f)
            }
        }.build()

        return _ShuffleIcon!!
    }

@Suppress("ObjectPropertyName")
private var _ShuffleIcon: ImageVector? = null


