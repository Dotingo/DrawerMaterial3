package com.dotingo.randomizer.presentation.ui.icons.drawer_icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val MusicRecordIcon: ImageVector
    get() {
        if (_MusicRecordIcon != null) {
            return _MusicRecordIcon!!
        }
        _MusicRecordIcon = ImageVector.Builder(
            name = "MusicRecord",
            defaultWidth = 26.dp,
            defaultHeight = 26.dp,
            viewportWidth = 120f,
            viewportHeight = 120f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF303037)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(59.5f, 107f)
                curveTo(85.73f, 107f, 107f, 85.73f, 107f, 59.5f)
                curveTo(107f, 33.27f, 85.73f, 12f, 59.5f, 12f)
                curveTo(33.27f, 12f, 12f, 33.27f, 12f, 59.5f)
                curveTo(12f, 85.73f, 33.27f, 107f, 59.5f, 107f)
                close()
                moveTo(59.5f, 76f)
                curveTo(68.61f, 76f, 76f, 68.61f, 76f, 59.5f)
                curveTo(76f, 50.39f, 68.61f, 43f, 59.5f, 43f)
                curveTo(50.39f, 43f, 43f, 50.39f, 43f, 59.5f)
                curveTo(43f, 68.61f, 50.39f, 76f, 59.5f, 76f)
                close()
                moveTo(43.22f, 78.08f)
                lineTo(41.92f, 76.78f)
                curveTo(40.89f, 75.75f, 39.27f, 75.61f, 38.08f, 76.44f)
                lineTo(30.93f, 81.45f)
                curveTo(29.4f, 82.52f, 29.21f, 84.71f, 30.53f, 86.03f)
                lineTo(33.97f, 89.47f)
                curveTo(35.29f, 90.79f, 37.48f, 90.6f, 38.55f, 89.07f)
                lineTo(43.56f, 81.92f)
                curveTo(44.39f, 80.73f, 44.25f, 79.11f, 43.22f, 78.08f)
                close()
                moveTo(78.09f, 43.24f)
                lineTo(76.8f, 41.93f)
                curveTo(75.77f, 40.9f, 75.64f, 39.28f, 76.48f, 38.09f)
                lineTo(81.53f, 30.97f)
                curveTo(82.61f, 29.44f, 84.8f, 29.26f, 86.11f, 30.59f)
                lineTo(89.53f, 34.05f)
                curveTo(90.85f, 35.38f, 90.64f, 37.57f, 89.11f, 38.63f)
                lineTo(81.93f, 43.6f)
                curveTo(80.73f, 44.42f, 79.11f, 44.27f, 78.09f, 43.24f)
                close()
            }
            path(fill = SolidColor(Color(0xFF303037))) {
                moveTo(59.5f, 59.5f)
                moveToRelative(-3.5f, 0f)
                arcToRelative(3.5f, 3.5f, 0f, isMoreThanHalf = true, isPositiveArc = true, 7f, 0f)
                arcToRelative(3.5f, 3.5f, 0f, isMoreThanHalf = true, isPositiveArc = true, -7f, 0f)
            }
        }.build()

        return _MusicRecordIcon!!
    }

@Suppress("ObjectPropertyName")
private var _MusicRecordIcon: ImageVector? = null
