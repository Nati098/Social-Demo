package ru.social.demo.pages.wiki.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import ru.social.demo.R
import ru.social.demo.ui.components.containers.OutlinedContainer
import ru.social.demo.ui.theme.BgWikiCharacter
import ru.social.demo.ui.theme.BgWikiCompendium
import ru.social.demo.ui.theme.BgWikiEvent
import ru.social.demo.ui.theme.BgWikiTale
import ru.social.demo.ui.theme.BgWikiWorld
import ru.social.demo.ui.theme.FgPrimary
import ru.social.demo.ui.theme.FgSecondary


enum class WikiTypeRes(
    val color: Color,
    @DrawableRes val bgImage: Int,
    @StringRes val titleId: Int,
    @StringRes val descriptionId: Int?
) {
    COMPENDIUM(BgWikiCompendium, R.drawable.bg_image_1, R.string.wiki_compendium, null),
    EVENT(BgWikiEvent, R.drawable.bg_image_7, R.string.wiki_event, null),
    WORLD(BgWikiWorld, R.drawable.bg_image_6, R.string.wiki_world, null),
    CHARACTER(BgWikiCharacter, R.drawable.bg_image_1, R.string.wiki_character, R.string.wiki_character),
    TALE(BgWikiTale, R.drawable.bg_image_3, R.string.wiki_tales, null)
}

@Composable
fun WikiTile(type: WikiTypeRes) {
    OutlinedContainer {

        val tileHeight = 108.dp
        val bgSize = 160.dp
        val imgSize = 0.56*bgSize

        Box(
            modifier = Modifier
                .height(tileHeight)
                .fillMaxWidth()
                .graphicsLayer {
                    compositingStrategy = CompositingStrategy.Offscreen
                    clip = true
                }
                .drawWithCache {
                    val path = Path()
                    path.addRect(
                        Rect(
                            topLeft = Offset.Zero,
                            bottomRight = Offset(this.size.width, this.size.height)
                        )
                    )
                    onDrawWithContent {
                        clipPath(path) { this@onDrawWithContent.drawContent() }
                    }
                }
        ) {
            Image(
                painter = painterResource(type.bgImage),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(width = bgSize, height = bgSize)
                    .align(Alignment.CenterStart)
                    .offset(x = -0.4*bgSize),
                colorFilter = ColorFilter.tint(type.color)
            )
        }

        Row(
            modifier = Modifier.matchParentSize(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painterResource(R.drawable.book),
                null,
                modifier = Modifier.size(imgSize).padding(horizontal = 12.dp)
            )
            Column(
                modifier = Modifier.padding(start = 12.dp, end = 20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        stringResource(type.titleId),
                        style = MaterialTheme.typography.headlineSmall,
                        color = FgPrimary
                    )
                    Image(
                        painterResource(R.drawable.ic_arrow_right),
                        null,
                        colorFilter = ColorFilter.tint(color = FgSecondary)
                    )
                }
                type.descriptionId?.let {
                    Text(
                        stringResource(it),
                        style = MaterialTheme.typography.bodyMedium,
                        color = FgSecondary
                    )
                }
            }
        }

    }
}
