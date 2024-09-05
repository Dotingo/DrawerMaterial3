package com.example.randomizer.presentation.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.max

@Composable
fun Modifier.verticalScrollbar(
    state: ScrollState,
    width: Dp = 2.dp,
    minHeight: Dp = 5.dp,
    color: Color = MaterialTheme.colorScheme.secondary,
    cornerRadius: Dp = 2.dp
): Modifier = composed {
    val targetAlpha = if (state.isScrollInProgress) 1f else 0f
    val duration = if (state.isScrollInProgress) 150 else 500

    val alpha by animateFloatAsState(
        targetValue = targetAlpha,
        animationSpec = tween(durationMillis = duration),
        label = ""
    )

    drawWithContent {
        drawContent()

        val needDrawScrollbar = state.isScrollInProgress || alpha > 0.0f

        if (needDrawScrollbar && state.maxValue > 0) {
            val visibleHeight: Float = this.size.height - state.maxValue
            val scrollBarHeight: Float =
                max(visibleHeight * (visibleHeight / this.size.height), minHeight.toPx())
            val scrollPercent: Float = state.value.toFloat() / state.maxValue
            val scrollBarOffsetY: Float =
                state.value + (visibleHeight - scrollBarHeight) * scrollPercent

            drawRoundRect(
                color = color,
                topLeft = Offset(this.size.width - width.toPx(), scrollBarOffsetY),
                size = Size(width.toPx(), scrollBarHeight),
                alpha = alpha,
                cornerRadius = CornerRadius(cornerRadius.toPx())
            )
        }
    }
}

@Composable
fun Modifier.lazyVerticalScrollbar(
    state: LazyListState,
    width: Dp = 2.dp,
    color: Color = MaterialTheme.colorScheme.secondary,
    cornerRadius: Dp = 2.dp
): Modifier {
    val targetAlpha = if (state.isScrollInProgress) 1f else 0f
    val duration = if (state.isScrollInProgress) 150 else 500

    val alpha by animateFloatAsState(
        targetValue = targetAlpha,
        animationSpec = tween(durationMillis = duration),
        label = ""
    )

    return drawWithContent {
        drawContent()

        val firstVisibleElementIndex = state.layoutInfo.visibleItemsInfo.firstOrNull()?.index
        val needDrawScrollbar = state.isScrollInProgress || alpha > 0.0f

        if (needDrawScrollbar && firstVisibleElementIndex != null) {
            val elementHeight = this.size.height / state.layoutInfo.totalItemsCount
            val scrollbarOffsetY = firstVisibleElementIndex * elementHeight
            val scrollbarHeight = state.layoutInfo.visibleItemsInfo.size * elementHeight

            drawRoundRect(
                color = color,
                topLeft = Offset(this.size.width - width.toPx(), scrollbarOffsetY),
                size = Size(width.toPx(), scrollbarHeight),
                alpha = alpha,
                cornerRadius = CornerRadius(cornerRadius.toPx())
            )
        }
    }
}