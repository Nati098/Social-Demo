package ru.social.demo.pages.wiki

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.social.demo.R
import ru.social.demo.data.model.TEMP_USER
import ru.social.demo.pages.wiki.components.WikiAppBarTile
import ru.social.demo.pages.wiki.components.WikiTile
import ru.social.demo.pages.wiki.components.WikiTypeRes
import ru.social.demo.ui.components.appbars.CAppBar
import ru.social.demo.ui.components.buttons.fab.Fab
import ru.social.demo.ui.components.buttons.fab.FabItem

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WikiPage() {

    val listState = rememberLazyListState()
    Scaffold(
        floatingActionButton = {
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
                    },
                    object : FabItem(
                        id = "new_post_event",
                        iconId = R.drawable.ic_user_edit,
                        label = R.string.post_type_character
                    ) {
                        override fun onClick() { }
                    }
                )
            )
        }
    ) { _ ->
        CAppBar(
            title = stringResource(R.string.wiki),
            user = TEMP_USER,
            state = listState,
            topBarContent = {
                // val fraction = this.fraction
                Row(
                    modifier = Modifier
                        .padding(bottom = 32.dp, top = 20.dp, start = 20.dp, end = 20.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    WikiAppBarTile(
                        imageId = R.drawable.ic_media,
                        titleId = R.string.wiki_media,
                        descriptionId = R.string.wiki_media_desc,
                        modifier = Modifier.weight(1f)
                    )
                    WikiAppBarTile(
                        imageId = R.drawable.ic_folders,
                        titleId = R.string.wiki_folders,
                        descriptionId = R.string.wiki_folders_desc,
                        modifier = Modifier.weight(1f)
                    )
                }
            },
            columnContent = { insets ->
                LazyColumn(
                    state = listState,
                    contentPadding = insets
                ) {
                    items(WikiTypeRes.entries.toTypedArray()) { type ->
                        WikiTile(type = type)
                        HorizontalDivider(thickness = 10.dp, color = Color.Transparent)
                    }
                    item {
                        Spacer(Modifier.size(60.dp))
                    }
                }
            }
        )
    }

}
