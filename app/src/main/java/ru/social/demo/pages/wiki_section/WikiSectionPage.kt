package ru.social.demo.pages.wiki_section

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import ru.social.demo.pages.wiki.components.WikiTypeRes
import ru.social.demo.ui.components.appbars.CTopBar
import ru.social.demo.ui.components.buttons.FilterButton
import ru.social.demo.ui.components.containers.RefreshContainer

const val WIKI_SECTION_TYPE = "wikiSectionType"

@Composable
fun WikiSectionPage(
    type: WikiTypeRes,
    viewModel: WikiSectionViewModel,
    navigateBack: () -> Unit
) {

    val dataViewSTate = viewModel.dataViewState.observeAsState()
    val listState = rememberLazyListState()

    CTopBar(
        title = stringResource(type.titleId),
        bgColor = colorResource(type.color),
        onBack = navigateBack,
        actions = {
            FilterButton(onClick = { })
        },
        content = { insets ->
            RefreshContainer(
                onRefresh = { viewModel.handle(WikiSectionContract.Event.Reload) }
            ) {
                LazyColumn(
                    state = listState,
                    contentPadding = insets
                ) {


                }
            }

        }
    )

}
