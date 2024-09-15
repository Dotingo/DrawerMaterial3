package com.dotingo.randomizer.presentation.ui.icons.list_navigate_icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val OutlinedPickIcon: ImageVector
    get() {
        if (_OutlinedPickIcon != null) {
            return _OutlinedPickIcon!!
        }
        _OutlinedPickIcon = ImageVector.Builder(
            name = "Pick",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 26f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFF303037)),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(7.151f, 17.27f)
                lineTo(5.35f, 14.88f)
                curveTo(5.096f, 14.537f, 4.973f, 14.114f, 5.005f, 13.689f)
                curveTo(5.036f, 13.263f, 5.22f, 12.863f, 5.522f, 12.561f)
                curveTo(5.824f, 12.259f, 6.224f, 12.076f, 6.65f, 12.045f)
                curveTo(7.076f, 12.013f, 7.499f, 12.136f, 7.842f, 12.39f)
                lineTo(9.993f, 14f)
                verticalLineTo(5f)
                curveTo(9.993f, 4.47f, 10.204f, 3.961f, 10.579f, 3.586f)
                curveTo(10.955f, 3.211f, 11.464f, 3f, 11.994f, 3f)
                curveTo(12.525f, 3f, 13.034f, 3.211f, 13.41f, 3.586f)
                curveTo(13.785f, 3.961f, 13.996f, 4.47f, 13.996f, 5f)
                verticalLineTo(9f)
                horizontalLineTo(16.997f)
                curveTo(18.059f, 9f, 19.077f, 9.421f, 19.828f, 10.172f)
                curveTo(20.578f, 10.922f, 21f, 11.939f, 21f, 13f)
                verticalLineTo(14.34f)
                curveTo(21f, 16.071f, 20.438f, 17.755f, 19.399f, 19.14f)
                lineTo(19.199f, 19.4f)
                curveTo(18.826f, 19.897f, 18.343f, 20.3f, 17.787f, 20.578f)
                curveTo(17.231f, 20.855f, 16.618f, 21f, 15.997f, 21f)
                horizontalLineTo(12.995f)
                curveTo(12.066f, 21.001f, 11.15f, 20.786f, 10.318f, 20.373f)
                curveTo(9.487f, 19.96f, 8.762f, 19.36f, 8.202f, 18.62f)
                lineTo(7.151f, 17.27f)
                close()
            }
        }.build()

        return _OutlinedPickIcon!!
    }

@Suppress("ObjectPropertyName")
private var _OutlinedPickIcon: ImageVector? = null

val FilledPickIcon: ImageVector
    get() {
        if (_FilledPickIcon != null) {
            return _FilledPickIcon!!
        }
        _FilledPickIcon = ImageVector.Builder(
            name = "PickFilled",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFF303037))) {
                moveTo(5.893f, 18.03f)
                lineTo(3.983f, 15.51f)
                curveTo(3.708f, 15.148f, 3.573f, 14.698f, 3.604f, 14.243f)
                curveTo(3.636f, 13.789f, 3.831f, 13.362f, 4.153f, 13.041f)
                curveTo(4.476f, 12.719f, 4.904f, 12.526f, 5.358f, 12.497f)
                curveTo(5.812f, 12.467f, 6.262f, 12.603f, 6.623f, 12.88f)
                lineTo(8.893f, 14.61f)
                verticalLineTo(5.11f)
                curveTo(8.893f, 4.833f, 8.948f, 4.559f, 9.054f, 4.303f)
                curveTo(9.16f, 4.047f, 9.315f, 3.814f, 9.511f, 3.618f)
                curveTo(9.707f, 3.422f, 9.94f, 3.267f, 10.196f, 3.161f)
                curveTo(10.452f, 3.055f, 10.726f, 3f, 11.003f, 3f)
                curveTo(11.28f, 3f, 11.555f, 3.055f, 11.811f, 3.161f)
                curveTo(12.067f, 3.267f, 12.299f, 3.422f, 12.495f, 3.618f)
                curveTo(12.691f, 3.814f, 12.847f, 4.047f, 12.953f, 4.303f)
                curveTo(13.059f, 4.559f, 13.113f, 4.833f, 13.113f, 5.11f)
                verticalLineTo(9.34f)
                horizontalLineTo(16.273f)
                curveTo(16.828f, 9.339f, 17.377f, 9.447f, 17.89f, 9.659f)
                curveTo(18.402f, 9.87f, 18.868f, 10.181f, 19.26f, 10.573f)
                curveTo(19.652f, 10.965f, 19.963f, 11.431f, 20.175f, 11.943f)
                curveTo(20.386f, 12.456f, 20.494f, 13.005f, 20.493f, 13.56f)
                verticalLineTo(14.97f)
                curveTo(20.492f, 16.794f, 19.903f, 18.568f, 18.813f, 20.03f)
                lineTo(18.603f, 20.31f)
                curveTo(18.21f, 20.836f, 17.7f, 21.262f, 17.113f, 21.556f)
                curveTo(16.527f, 21.849f, 15.879f, 22.001f, 15.223f, 22f)
                horizontalLineTo(12.053f)
                curveTo(11.083f, 21.991f, 10.128f, 21.76f, 9.262f, 21.325f)
                curveTo(8.395f, 20.89f, 7.64f, 20.262f, 7.053f, 19.49f)
                lineTo(5.893f, 18.03f)
                close()
            }
        }.build()

        return _FilledPickIcon!!
    }

@Suppress("ObjectPropertyName")
private var _FilledPickIcon: ImageVector? = null
