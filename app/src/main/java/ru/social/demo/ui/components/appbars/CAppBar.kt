package ru.social.demo.ui.components.appbars

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
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
import ru.social.demo.base.NavPath
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
    navController: NavController? = null,
    topInset: Boolean = true,
    topBarContent: @Composable CollapsibleTopAppBarScope.() -> Unit,
    columnContent: @Composable (insets: PaddingValues) -> Unit
) {
    CollapsibleScaffold(
        state = state,
        topInset = topInset,
        content = columnContent,
        topBar = {
            TopBar(
                modifier = Modifier
                    .clip(shape = SDTheme.shapes.appBarCorners)
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
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            navController?.navigate(NavPath.profile)
                        },
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
