package ru.social.demo.ui.components.containers

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.social.demo.ui.theme.SDTheme

@Composable
fun OutlinedContainer(
    parentWidth: Boolean = false,
    paddingHorizontal: Dp = 0.dp,
    paddingVertical: Dp = 0.dp,
    modifier: Modifier = Modifier,
    content: @Composable (BoxScope.() -> Unit)
) {

    RoundedContainer(
        parentWidth = parentWidth,
        paddingHorizontal = paddingHorizontal,
        paddingVertical = paddingVertical,
        shape = SDTheme.shapes.corners,
        bgColor = SDTheme.colors.bgPrimary,
        modifier = modifier.border(
            1.5.dp,
            SDTheme.colors.borderColor,
            shape = SDTheme.shapes.corners
        ),
        content = content
    )

}
