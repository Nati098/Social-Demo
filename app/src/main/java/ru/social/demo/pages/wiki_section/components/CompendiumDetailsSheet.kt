package ru.social.demo.pages.wiki_section.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.social.demo.R
import ru.social.demo.data.model.rpg.RpgClass
import ru.social.demo.data.model.rpg.RpgMonster
import ru.social.demo.data.model.rpg.RpgRace
import ru.social.demo.pages.EmptyPage
import ru.social.demo.ui.components.CProgressIndicator
import ru.social.demo.ui.theme.SDTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompendiumDetailsSheet(
    data: Any?,
    isBottomSheetVisible: Boolean,
    sheetState: SheetState,
    onDismissRequest: () -> Unit
) {
    if (isBottomSheetVisible) {

        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = onDismissRequest,
            dragHandle = null,
            shape = RectangleShape,
            containerColor = Color.Transparent,
            contentWindowInsets = { WindowInsets(0,0,0,0) },
        ) {

            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .statusBarsPadding()
                    .navigationBarsPadding()
                    .padding(16.dp)
                    .clip(SDTheme.shapes.corners)
                    .background(color = SDTheme.colors.bgPrimary)
                    .fillMaxWidth()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                when(data) {
                    is RpgClass -> ClassDetails(data)
                    is RpgRace -> RaceDetails(data)
                    is RpgMonster -> MonsterDetails(data)
                    null -> EmptyPage(
                        title = "Oops!",
                        description = stringResource(R.string.error_loading_desc)
                    )
                    else -> CProgressIndicator()
                }
            }
        }
    }
}

@Composable
private fun ColumnScope.ClassDetails(data: RpgClass) {

    Block(
        title = "${data.name}"
    )

    SubBlock(
        desc = String.format(
            stringResource(R.string.class_data),
            data.hitDie, "${data.spellcasting?.level ?: "-"}"
        )
    )

    Block(
        title = String.format(stringResource(R.string.subclasses)),
        subblocks = listOf { SubBlock(desc = data.subclasses?.map { it.name }?.joinToString()) }
    )

    Block(
        title = String.format(stringResource(R.string.proficiencies)),
        subblocks = listOf { SubBlock(desc = data.proficiencies?.map { it.name }?.joinToString()) }
    )

    data.spellcasting?.let { sc ->
        Block(
            title = stringResource(R.string.spellcasting),
            subblocks = mutableListOf<@Composable () -> Unit>().apply {
                add { SubBlock(desc = sc.ability?.name) }
                sc.info?.forEach { info ->
                    add { SubBlock(title = info.name, desc = info.desc?.joinToString("\n")) }
                }
            }
        )
    }

}

@Composable
private fun ColumnScope.RaceDetails(data: RpgRace) {

    Block(
        title = "${data.name}"
    )
    SubBlock(
        desc = String.format(
            stringResource(R.string.race_data),
            data.sizeDesc, data.speed
        )
    )

    data.subraces?.let { sr ->
        Block(
            title = stringResource(R.string.subraces),
            subblocks = listOf { SubBlock(desc = sr.map { it.name }.joinToString())}
        )
    }

    Block(
        title = stringResource(R.string.flavor),
        subblocks = listOf { SubBlock(desc = data.alignment) }
    )
    Block(
        title = stringResource(R.string.age),
        subblocks = listOf { SubBlock(desc = data.age) }
    )

    Block(
        title = stringResource(R.string.languages),
        subblocks = listOf {
            SubBlock(desc = data.languages?.joinToString())
            SubBlock(desc = data.languageDesc)
        }
    )

    data.traits?.let { trait ->
        Block(
            title = stringResource(R.string.traits),
            subblocks = listOf { SubBlock(desc = trait.map { it.name }.joinToString())}
        )
    }
}

@Composable
private fun ColumnScope.MonsterDetails(data: RpgMonster) {

    Block(
        title = "${data.name}",
        subblocks = listOf { SubBlock(desc = data.desc) }
    )

    SubBlock(
        desc = String.format(
            stringResource(R.string.monster_data),
            data.size, data.type, data.subtype ?: "-", data.hp
        )
    )

    if (!data.abilities.isNullOrEmpty()) {
        Block(
            title = stringResource(R.string.abilities),
            subblocks = data.abilities.map {
                { SubBlock(
                    title = it.name,
                    desc = it.desc
                ) }
            }
        )
    }

}


@Composable
private fun Block(title: String, subblocks: List<@Composable () -> Unit>? = null) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            title,
            style = SDTheme.typography.headingS,
            color = SDTheme.colors.fgPrimary
        )

        subblocks?.forEach { it.invoke() }
    }
}

@Composable
private fun SubBlock(title: String? = null, desc: String?) {
    Log.d("TEST", "$title desc $desc")
    Column {
        title?.let {
            Text(
                it,
                style = SDTheme.typography.bodyMediumL,
                color = SDTheme.colors.fgPrimary
            )
        }

        if (!desc.isNullOrBlank()) {
            Text(
                desc,
                style = SDTheme.typography.bodyMediumS,
                color = SDTheme.colors.fgSecondary
            )
        }
    }
}
