package ru.social.demo.ui.components.containers

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.social.demo.ui.theme.BgPrimary
import ru.social.demo.ui.theme.BorderSecondary

@Composable
fun OutlinedContainer(
    parentWidth: Boolean = false,
    paddingHorizontal: Dp = 0.dp,
    paddingVertical: Dp = 0.dp,
    modifier: Modifier = Modifier,
    content: @Composable (BoxScope.() -> Unit)
) {

    // TODO: problem with border and corners
//    RoundedContainer(
//        parentWidth = parentWidth,
//        paddingHorizontal = paddingHorizontal,
//        paddingVertical = paddingVertical,
//        cornerSize = 20.dp,
//        bgColor = BgPrimary,
//        modifier = modifier,
//        content = content
//    )

    val modifierr = if (parentWidth) modifier.fillMaxWidth() else modifier.wrapContentSize()

    Box(
        modifier = modifierr
            .border(
                1.5.dp,
                BorderSecondary,
                shape = RoundedCornerShape(20f)
            )
            .clip(shape = RoundedCornerShape(20f))
            .background(color = BgPrimary)
            .padding(horizontal = paddingHorizontal, vertical = paddingVertical),
        contentAlignment = Alignment.TopCenter,
        content = content
    )
}