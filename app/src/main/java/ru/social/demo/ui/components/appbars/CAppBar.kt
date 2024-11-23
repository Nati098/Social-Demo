package ru.social.demo.ui.components.appbars

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import ru.social.demo.R
import ru.social.demo.data.model.TEMP_POST1
import ru.social.demo.data.model.User
import ru.social.demo.services.FirestoreInteractor
import ru.social.demo.services.FsPath
import ru.social.demo.ui.components.Avatar
import ru.social.demo.ui.components.appbars.utils.CollapsibleScaffold
import ru.social.demo.ui.components.appbars.utils.CollapsibleTopAppBarScope
import ru.social.demo.ui.components.appbars.utils.TopBar
import ru.social.demo.ui.components.buttons.CIconButtonOutlined
import ru.social.demo.ui.theme.CWhite
import ru.social.demo.ui.theme.SDTheme

/**
 * Collapsing AppBar with fading content and LazyColumn in body
 *
 * @param title String, name of the page
 * @param state State of list in columnContent
 * @param topBarContent Content in bar area, fading and disappearing while is being scrolled
 * @param columnContent LazyColumn (!) with scrolling content
 */
@Composable
fun CAppBar(
    title: String = "",
    user: User,
    state: LazyListState,
    topBarContent: @Composable CollapsibleTopAppBarScope.() -> Unit,
    columnContent: @Composable (insets: PaddingValues) -> Unit
) {
    CollapsibleScaffold(
        state = state,
        content = columnContent,
        topBar = {
            TopBar(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
                    .background(SDTheme.colors.bgSecondary),
                title = title,
                onBack = null,
                actions = {
                    CIconButtonOutlined(
                        iconId = R.drawable.ic_search,
                        bgColor = CWhite,
                        onClick = { FirestoreInteractor.getInstance().setData(FsPath.POSTS, TEMP_POST1) }
                        // TODO remove
                    )
                    CIconButtonOutlined(
                        iconId = R.drawable.ic_bell,
                        bgColor = CWhite,
                        onClick = { }
                    )
                    Avatar(
                        imgUrl = user.imageUrl,
                        char = user.name?.get(0) ?: 'U',
                        size = 44.dp
                    )
                },
                content = topBarContent
            )
        }
    )

}
