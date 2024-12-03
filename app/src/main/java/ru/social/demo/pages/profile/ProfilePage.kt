package ru.social.demo.pages.profile

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.social.demo.MainContract
import ru.social.demo.MainViewModel
import ru.social.demo.R
import ru.social.demo.data.model.User
import ru.social.demo.pages.profile.components.FriendsInfoBlock
import ru.social.demo.pages.profile.components.UserInfoBlock
import ru.social.demo.ui.components.ArrowTile
import ru.social.demo.ui.components.Avatar
import ru.social.demo.ui.components.appbars.CTopBar
import ru.social.demo.ui.components.buttons.ShareButton
import ru.social.demo.ui.components.buttons.UserEditButton
import ru.social.demo.ui.components.containers.RefreshContainer
import ru.social.demo.ui.theme.SDTheme

@Composable
fun ProfilePage(
    navigateBack: () -> Unit
) {

    val mainViewModel: MainViewModel = viewModel(LocalContext.current as ComponentActivity)
    val userViewState by mainViewModel.userViewState.observeAsState()

    val listState = rememberLazyListState()

    CTopBar(
        title = stringResource(R.string.profile),
        onBack = navigateBack,
        actions = {
            UserEditButton(onClick = { })
            ShareButton(
                LocalContext.current,
                bundleOf(
                    "user" to ((userViewState as? MainContract.State.SuccessUser)?.data)
                )
            )
        },
        content = { insets ->
            RefreshContainer(
                onRefresh = { mainViewModel.handle(MainContract.Event.Reload) }
            ) {
                LazyColumn(
                    state = listState,
                    contentPadding = insets
                ) {
                    when (userViewState) {
                        is MainContract.State.SuccessUser -> item {
                            HeaderBlock((userViewState as MainContract.State.SuccessUser).data)
                        }
                        else -> item { HeaderBlock() }
                    }

                    item { DetailsSpacer() }

                    when (userViewState) {
                        is MainContract.State.SuccessUser -> item {
                            DetailsBlock((userViewState as MainContract.State.SuccessUser).data)
                        }
                        else -> item { DetailsBlock() }
                    }

                    item { DetailsSpacer() }

                    items(userSections()) { it.invoke() }

                }
            }

        }
    )

}


@Composable
private fun HeaderBlock(user: User? = null) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Avatar(
            size = 96.dp,
            imgUrl = user?.imageUrl,
            char = user?.name?.get(0),
            onClick = {}
        )
        user?.let {
            Text(
                "${it.name}",
                style = SDTheme.typography.headingS,
                color = SDTheme.colors.fgPrimary,
                textAlign = TextAlign.Center
            )
        }
        if(!user?.about.isNullOrBlank())
            Text(
                user?.about!!,
                style = SDTheme.typography.bodyMediumM,
                color = SDTheme.colors.fgTertiary,
                textAlign = TextAlign.Center
            )
//        Spacer(Modifier.height(12.dp))
//        CButton(
//            modifier = Modifier.width(260.dp),
//            label = stringResource(R.string.add_friends)
//        ) { }
    }

}

@Composable
private fun DetailsBlock(user: User? = null) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .padding(vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        UserInfoBlock(user)
        DetailsSpacer()
        FriendsInfoBlock(user?.friends)
    }
}

private fun userSections(): List<@Composable () -> Unit> = listOf(
    { ArrowTile(title = stringResource(R.string.teams), iconId = R.drawable.profile) },
    { ArrowTile(title = stringResource(R.string.worlds), iconId = R.drawable.ic_image) },
    { ArrowTile(title = stringResource(R.string.characters), iconId = R.drawable.ic_bird) },
)

@Composable
private fun DetailsSpacer() {
    Spacer(
        Modifier
            .height(1.dp)
            .fillMaxWidth()
            .background(SDTheme.colors.bgSecondary)
    )
}
