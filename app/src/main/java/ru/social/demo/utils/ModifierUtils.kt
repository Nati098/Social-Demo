package ru.social.demo.utils

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ScrollState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import kotlin.math.max

fun Modifier.verticalScrollbar(
    scrollState: ScrollState,
    color: Color,
    width: Float = 8f,
    minHeight: Float = 8f

): Modifier = composed {
    val duration = if (scrollState.isScrollInProgress) 150 else 500
    val alpha by animateFloatAsState(
        label = "",
        targetValue = if (scrollState.isScrollInProgress) 1f else 0f,
        animationSpec = tween(durationMillis = duration)
    )

    drawWithContent {
        drawContent()

        val needDrawScrollbar = scrollState.isScrollInProgress || alpha > 0.0f

        if (needDrawScrollbar && scrollState.maxValue > 0) {
            val visibleHeight = this.size.height - scrollState.maxValue
            val scrollBarHeight = max(visibleHeight * (visibleHeight / this.size.height), minHeight)
            val scrollPercent = scrollState.value.toFloat() / scrollState.maxValue
            val scrollBarOffsetY = scrollState.value + (visibleHeight - scrollBarHeight) * scrollPercent

            drawRoundRect(
                color = color,
                topLeft = Offset(this.size.width - width, scrollBarOffsetY),
                size = Size(width, scrollBarHeight),
                alpha = alpha,
                cornerRadius = CornerRadius(20f)
            )
        }
    }
}