package ru.social.demo.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.social.demo.R
import ru.social.demo.ui.theme.SDTheme

@Composable
fun ArrowTile(
    title: String,
    description: String? = null,
    @DrawableRes iconId: Int? = null
) {
    val image = if (iconId == null) {
        ArrowTileInternal(
            title = title,
            description = description
        )
    } else {
        ArrowTileInternal(
            title = title,
            description = description,
            icon = {
                Image(
                    painterResource(iconId),
                    null,
                    Modifier.size(24.dp)
                )
            }
        )
    }
}

@Composable
fun ArrowTile(
    title: String,
    description: String? = null,
    icon: (@Composable () -> Unit)? = null
) {
    ArrowTileInternal(
        title = title,
        description = description,
        icon = icon
    )
}

@Composable
private fun ArrowTileInternal(
    title: String,
    description: String? = null,
    icon: (@Composable () -> Unit)? = null
) {

    Row(
        modifier = Modifier
            .padding(vertical = 16.dp, horizontal = 16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        icon?.let {
            it.invoke()
            Spacer(modifier = Modifier.size(12.dp))
        }

        Column {
            Text(
                title,
                style = SDTheme.tyrography.bodyBoldL,
                color = SDTheme.colors.fgPrimary
            )
            description?.let {
                Text(
                    it,
                    style = SDTheme.tyrography.bodyMediumS,
                    color = SDTheme.colors.fgSecondary
                )
            }

        }

        Spacer(modifier = Modifier.weight(1f))
        Image(
            painterResource(R.drawable.ic_arrow_right),
            null,
            colorFilter = ColorFilter.tint(color = SDTheme.colors.fgSecondary)
        )
    }

}