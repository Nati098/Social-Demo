package ru.social.demo.pages.wiki_section.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.social.demo.R
import ru.social.demo.data.model.rpg.RpgClass
import ru.social.demo.data.model.rpg.RpgRace
import ru.social.demo.data.model.rpg.RpgTab
import ru.social.demo.data.model.rpg.ShortInfo
import ru.social.demo.pages.EmptyPage
import ru.social.demo.pages.wiki_section.WikiSectionContract
import ru.social.demo.pages.wiki_section.WikiSectionViewModel
import ru.social.demo.ui.components.ArrowTile
import ru.social.demo.ui.components.CTabRow
import ru.social.demo.ui.components.containers.RefreshContainer

@Composable
fun CompendiumPage(
    insets: PaddingValues,
    viewModel: WikiSectionViewModel
) {
    val tabs = RpgTab.entries
    val dataViewState = viewModel.compendiumState.observeAsState()

    RefreshContainer(
        onRefresh = {
            val selectedTab = dataViewState.value?.selectedTab ?: 0
            viewModel.handle(WikiSectionContract.Event.Reload(tabs[selectedTab]))
        }
    ) {
        Column(Modifier.padding(insets).fillMaxSize()) {
            CTabRow(
                tabs = tabs,
                tabTitle = { item -> item.label() },
                onClick = { index, item ->
                    viewModel.handle(WikiSectionContract.Event.LoadData(type = item))
                    viewModel.handle(WikiSectionContract.Event.TabChanged(idx = index))
                }
            )

            when(dataViewState.value?.selectedTab) {
                0 -> ClassesList(dataViewState.value?.classes)
                1 -> RacesList(dataViewState.value?.races)
                2 -> MonstersList(dataViewState.value?.monsters)
            }
        }
    }


    LaunchedEffect(Unit) {
        val selectedTab = dataViewState.value?.selectedTab ?: 0
        viewModel.handle(
            WikiSectionContract.Event.LoadData(tabs[selectedTab])
        )
    }

}

@Composable
private fun ClassesList(list: List<RpgClass>?) {
    if (list.isNullOrEmpty()) {
        EmptyState("classes")
    } else {
        val listState = rememberLazyListState()
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = listState
        ) {
            items(list) {
                ArrowTile(title = "${it.name}", description = "Class", icon = null)
            }
        }
    }
}

@Composable
private fun RacesList(list: List<RpgRace>?) {
    if (list.isNullOrEmpty()) {
        EmptyState("races")
    } else {
        val listState = rememberLazyListState()
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = listState
        ) {
            items(list) {
                ArrowTile(title = "${it.name}", description = "Race", icon = null)
            }
        }
    }
}

@Composable
private fun MonstersList(list: List<ShortInfo>?) {
    if (list.isNullOrEmpty()) {
        EmptyState("monsters")
    } else {
        val listState = rememberLazyListState()
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = listState
        ) {
            items(list) {
                ArrowTile(title = "${it.name}", description = "Monster", icon = null)
            }
        }
    }
}

@Composable
private fun EmptyState(type: String) {
    EmptyPage(
        stringResource(R.string.empty_title),
        String.format(stringResource(R.string.no_desc), type)
    )
}