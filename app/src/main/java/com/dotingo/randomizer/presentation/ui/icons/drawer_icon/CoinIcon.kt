package com.dotingo.randomizer.presentation.ui.icons.drawer_icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val CoinIcon: ImageVector
    get() {
        if (_CoinIcon != null) {
            return _CoinIcon!!
        }
        _CoinIcon = ImageVector.Builder(
            name = "Coin",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFF303037)),
                strokeLineWidth = 2f
            ) {
                moveTo(12f, 12f)
                moveToRelative(-10f, 0f)
                arcToRelative(10f, 10f, 0f, isMoreThanHalf = true, isPositiveArc = true, 20f, 0f)
                arcToRelative(10f, 10f, 0f, isMoreThanHalf = true, isPositiveArc = true, -20f, 0f)
            }
            path(
                stroke = SolidColor(Color(0xFF303037)),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(12f, 17.5f)
                verticalLineTo(16.38f)
            }
            path(
                stroke = SolidColor(Color(0xFF303037)),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(12f, 7.85f)
                verticalLineTo(6.5f)
            }
            path(
                stroke = SolidColor(Color(0xFF303037)),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(15f, 10f)
                curveTo(15f, 9.47f, 14.789f, 8.961f, 14.414f, 8.586f)
                curveTo(14.039f, 8.211f, 13.53f, 8f, 13f, 8f)
                horizontalLineTo(10.89f)
                curveTo(10.389f, 8f, 9.908f, 8.199f, 9.554f, 8.554f)
                curveTo(9.199f, 8.908f, 9f, 9.389f, 9f, 9.89f)
                curveTo(8.999f, 10.339f, 9.157f, 10.773f, 9.446f, 11.115f)
                curveTo(9.736f, 11.458f, 10.138f, 11.686f, 10.58f, 11.76f)
                lineTo(13.42f, 12.24f)
                curveTo(13.862f, 12.314f, 14.264f, 12.542f, 14.554f, 12.885f)
                curveTo(14.843f, 13.227f, 15.001f, 13.661f, 15f, 14.11f)
                curveTo(15f, 14.358f, 14.951f, 14.604f, 14.856f, 14.833f)
                curveTo(14.761f, 15.063f, 14.622f, 15.271f, 14.446f, 15.446f)
                curveTo(14.271f, 15.622f, 14.063f, 15.761f, 13.833f, 15.856f)
                curveTo(13.604f, 15.951f, 13.358f, 16f, 13.11f, 16f)
                horizontalLineTo(11f)
                curveTo(10.47f, 16f, 9.961f, 15.789f, 9.586f, 15.414f)
                curveTo(9.211f, 15.039f, 9f, 14.53f, 9f, 14f)
            }
        }.build()

        return _CoinIcon!!
    }

@Suppress("ObjectPropertyName")
private var _CoinIcon: ImageVector? = null
