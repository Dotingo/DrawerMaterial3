package com.dotingo.randomizer.presentation.ui.icons.drawer_icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val CountryIcon: ImageVector
    get() {
        if (_CountryIcon != null) {
            return _CountryIcon!!
        }
        _CountryIcon = ImageVector.Builder(
            name = "Globe",
            defaultWidth = 26.dp,
            defaultHeight = 26.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(fill = SolidColor(Color(0xFF303037))) {
                moveTo(480f, 880f)
                quadToRelative(-83f, 0f, -156f, -31.5f)
                reflectiveQuadTo(197f, 763f)
                quadToRelative(-54f, -54f, -85.5f, -127f)
                reflectiveQuadTo(80f, 480f)
                quadToRelative(0f, -83f, 31.5f, -156f)
                reflectiveQuadTo(197f, 197f)
                quadToRelative(54f, -54f, 127f, -85.5f)
                reflectiveQuadTo(480f, 80f)
                quadToRelative(83f, 0f, 156f, 31.5f)
                reflectiveQuadTo(763f, 197f)
                quadToRelative(54f, 54f, 85.5f, 127f)
                reflectiveQuadTo(880f, 480f)
                quadToRelative(0f, 83f, -31.5f, 156f)
                reflectiveQuadTo(763f, 763f)
                quadToRelative(-54f, 54f, -127f, 85.5f)
                reflectiveQuadTo(480f, 880f)
                close()
                moveTo(480f, 800f)
                quadToRelative(134f, 0f, 227f, -93f)
                reflectiveQuadToRelative(93f, -227f)
                quadToRelative(0f, -7f, -0.5f, -14.5f)
                reflectiveQuadTo(799f, 453f)
                quadToRelative(-5f, 29f, -27f, 48f)
                reflectiveQuadToRelative(-52f, 19f)
                horizontalLineToRelative(-80f)
                quadToRelative(-33f, 0f, -56.5f, -23.5f)
                reflectiveQuadTo(560f, 440f)
                verticalLineToRelative(-40f)
                lineTo(400f, 400f)
                verticalLineToRelative(-80f)
                quadToRelative(0f, -33f, 23.5f, -56.5f)
                reflectiveQuadTo(480f, 240f)
                horizontalLineToRelative(40f)
                quadToRelative(0f, -23f, 12.5f, -40.5f)
                reflectiveQuadTo(563f, 171f)
                quadToRelative(-20f, -5f, -40.5f, -8f)
                reflectiveQuadToRelative(-42.5f, -3f)
                quadToRelative(-134f, 0f, -227f, 93f)
                reflectiveQuadToRelative(-93f, 227f)
                horizontalLineToRelative(200f)
                quadToRelative(66f, 0f, 113f, 47f)
                reflectiveQuadToRelative(47f, 113f)
                verticalLineToRelative(40f)
                lineTo(400f, 680f)
                verticalLineToRelative(110f)
                quadToRelative(20f, 5f, 39.5f, 7.5f)
                reflectiveQuadTo(480f, 800f)
                close()
            }
        }.build()

        return _CountryIcon!!
    }

@Suppress("ObjectPropertyName")
private var _CountryIcon: ImageVector? = null
