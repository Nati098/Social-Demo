package ru.social.demo.pages.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import ru.social.demo.MainViewModel
import ru.social.demo.R
import ru.social.demo.data.model.Post
import ru.social.demo.pages.EmptyPage
import ru.social.demo.pages.home.components.PostTile
import ru.social.demo.pages.post_editor.PostEditorMode
import ru.social.demo.pages.post_editor.PostEditorSheet
import ru.social.demo.ui.components.CProgressIndicator
import ru.social.demo.ui.components.buttons.fab.Fab
import ru.social.demo.ui.components.appbars.CAppBar
import ru.social.demo.ui.components.buttons.fab.FabItem

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomePage(
    navController: NavController,
    viewModel: HomeViewModel
) {
    val mainViewModel: MainViewModel = viewModel(LocalContext.current as ComponentActivity)

    val viewState by viewModel.postsViewState.observeAsState()
    val postsListState = rememberLazyListState()

    Scaffold(
        floatingActionButton = { FabButton() }
    ) { _ ->
        CAppBar(
            title = stringResource(R.string.home),
            state = postsListState,
            navController = navController,
            topBarContent = { Carousel() },
            columnContent = { insets ->
                when(viewState) {
                    is HomeContract.State.LoadingData -> CProgressIndicator()
                    is HomeContract.State.SuccessData -> Feed(
                        (viewState as HomeContract.State.SuccessData).data,
                        insets,
                        postsListState
                    )
                    else -> EmptyPage(
                        title = "Oops!",
                        description = stringResource(R.string.error_loading_desc)
                    )
                }
            }
        )
    }

    LaunchedEffect(Unit) {
        Log.d("TEST", "Home, viewModel is $viewModel, mainVM $mainViewModel")
        viewModel.handle(HomeContract.Event.LoadData)
    }

}


@Composable
fun Feed(
    data: List<Post>?,
    insets: PaddingValues,
    postsListState: LazyListState
) {
    var showPostEditorSheet by remember { mutableStateOf(false) }
    var postToEditId by remember { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier.fillMaxHeight(),
        state = postsListState,
        contentPadding = insets
    ) {
        if (data.isNullOrEmpty()) {
            item {
                EmptyPage(
                    title = "Feed",
                    description = stringResource(R.string.no_posts_decs)
                )
            }
        } else {
            items(data) {
                PostTile(
                    it,
                    onEdit = {
                        showPostEditorSheet = true
                        postToEditId = it.id
                    }
                )
                HorizontalDivider(thickness = 10.dp, color = Color.Transparent)
            }
        }

    }

    if (showPostEditorSheet)
        PostEditorSheet(
            post = data!!.filter { it.id == postToEditId }[0],
            mode = PostEditorMode.EDIT,
            onDismissRequest = { showPostEditorSheet = false },
            modifier = Modifier
                .heightIn(max = LocalConfiguration.current.screenHeightDp.dp - 60.dp)
                .fillMaxHeight()
        )
}

@Composable
private fun Carousel() {
    Column(
        modifier = Modifier
            .padding(bottom = 32.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(156.dp)) {
            Text(
                text = "News, notifications and stories as list of cards",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
private fun FabButton() {
    Fab(
        items = listOf(
            object : FabItem(
                id = "new_post_default",
                iconId = R.drawable.ic_plus_circle,
                label = R.string.post_type_default
            ) {
                override fun onClick() { }
            },
            object : FabItem(
                id = "new_post_event",
                iconId = R.drawable.ic_calendar,
                label = R.string.post_type_event
            ) {
                override fun onClick() { }
            }
        )
    )
}
