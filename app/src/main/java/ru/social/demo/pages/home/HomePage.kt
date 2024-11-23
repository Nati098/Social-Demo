package ru.social.demo.pages.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.social.demo.R
import ru.social.demo.data.model.Post
import ru.social.demo.data.model.TEMP_USER
import ru.social.demo.pages.EmptyPage
import ru.social.demo.pages.home.components.PostTile
import ru.social.demo.services.FirestoreInteractor
import ru.social.demo.services.FsPath
import ru.social.demo.ui.components.buttons.fab.Fab
import ru.social.demo.ui.components.appbars.CAppBar
import ru.social.demo.ui.components.buttons.fab.FabItem

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomePage() {

    val postsList = remember { mutableStateOf(emptyList<Post>()) }
    val postsListState = rememberLazyListState()

    LaunchedEffect(Unit) {
        FirestoreInteractor.getInstance().readData<Post>(
            path = FsPath.POSTS,
            onSuccess = { result ->
                postsList.value = result?.sortedByDescending { it.createDate } ?: emptyList()
            }
        )
    }

    Scaffold(
        floatingActionButton = { FabMain() }
    ) { _ ->
        CAppBar(
            title = "Main",
            user = TEMP_USER,
            state = postsListState,
            topBarContent = { Carousel() },
            columnContent = { insets ->
                LazyColumn(
                    modifier = Modifier.fillMaxHeight(),
                    state = postsListState,
                    contentPadding = insets
                ) {
                    if (postsList.value.isEmpty()) {
                        item {
                            EmptyPage(
                                title = "Feed",
                                description = "As soon as you or your friends do something in the application, it will be here"
                            )
                        }
                    } else {
                        items(postsList.value) {
                            PostTile(it)
                            HorizontalDivider(thickness = 10.dp, color = Color.Transparent)
                        }
                    }

                }
            }
        )
    }
}

@Composable
private fun FabMain() {
    Fab(
        items = listOf(
            FabItem(
                id = "new_post_default",
                iconId = R.drawable.ic_plus_circle,
                label = R.string.post_type_default
            ),
            FabItem(
                id = "new_post_event",
                iconId = R.drawable.ic_calendar,
                label = R.string.post_type_event
            )
        )
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
