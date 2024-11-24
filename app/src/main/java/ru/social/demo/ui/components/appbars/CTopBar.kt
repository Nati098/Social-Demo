package ru.social.demo.ui.components.appbars

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.social.demo.ui.components.appbars.utils.CustomScaffold
import ru.social.demo.ui.components.appbars.utils.TopBar
import ru.social.demo.ui.theme.SDTheme

/**
 * TopBar with back button, title and some functions
 *
 * @param title String, name of the page
 * @param actions list of actions
 * @param content LazyColumn (!) with scrolling content
 */
@Composable
fun CTopBar(
    title: String = "",
    bgColor: Color = SDTheme.colors.bgSecondary,
    modifier: Modifier = Modifier,
    topInset: Boolean = true,
    actions: @Composable (RowScope.() -> Unit)? = null,
    content: @Composable (insets: PaddingValues) -> Unit
) {
    CustomScaffold(
        modifier = modifier,
        topInset = topInset,
        content = content,
        topBar = {
            TopBar(
                modifier = Modifier
                    .clip(shape = SDTheme.shapes.appBarCorners)
                    .background(bgColor),
                title = title,
                onBack = { },
                actions = actions
            )
        }
    )

}
