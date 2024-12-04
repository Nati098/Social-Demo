package ru.social.demo.pages.wiki_section

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import ru.social.demo.R
import ru.social.demo.pages.EmptyPage
import ru.social.demo.pages.wiki.components.WikiTypeRes
import ru.social.demo.pages.wiki_section.components.CompendiumPage
import ru.social.demo.ui.components.appbars.CTopBar
import ru.social.demo.ui.components.buttons.FilterButton

const val WIKI_SECTION_TYPE = "wikiSectionType"

@Composable
fun WikiSectionPage(
    type: WikiTypeRes,
    viewModel: WikiSectionViewModel,
    navigateBack: () -> Unit
) {

    CTopBar(
        title = stringResource(type.titleId),
        bgColor = colorResource(type.color),
        onBack = navigateBack,
        actions = {
            FilterButton(onClick = { })
        },
        content = { insets ->
            when(type) {
                WikiTypeRes.COMPENDIUM -> CompendiumPage(insets, viewModel)
                else -> EmptyPage(
                    stringResource(R.string.empty_title),
                    String.format(stringResource(R.string.no_desc), "data")
                )
            }

        }
    )

}
