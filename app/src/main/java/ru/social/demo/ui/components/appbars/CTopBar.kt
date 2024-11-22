package ru.social.demo.ui.components.appbars

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import ru.social.demo.ui.components.appbars.utils.CustomScaffold
import ru.social.demo.ui.components.appbars.utils.TopBar
import ru.social.demo.ui.theme.BgSecondary

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
    actions: @Composable (RowScope.() -> Unit)? = null,
    content: @Composable (insets: PaddingValues) -> Unit
) {
    CustomScaffold(
        content = content,
        topBar = {
            TopBar(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
                    .background(BgSecondary),
                title = title,
                onBack = { },
                actions = actions
            )
        }
    )

}
