package ru.social.demo.pages.wiki.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.social.demo.ui.components.containers.OutlinedContainer
import ru.social.demo.ui.theme.BgHighlight
import ru.social.demo.ui.theme.FgActionEmphasis
import ru.social.demo.ui.theme.FgPrimary
import ru.social.demo.ui.theme.FgSecondary

@Composable
fun WikiAppBarTile(
    @DrawableRes imageId: Int,
    @StringRes titleId: Int,
    @StringRes descriptionId: Int,
    modifier: Modifier = Modifier
) {
    OutlinedContainer(
        modifier = modifier,
        paddingVertical = 20.dp,
        paddingHorizontal = 20.dp
    ) {
        Column {
            Icon(imageId)
            Spacer(modifier = Modifier.size(12.dp))
            Text(
                stringResource(titleId),
                style = MaterialTheme.typography.headlineSmall,
                color = FgPrimary
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                stringResource(descriptionId),
                style = MaterialTheme.typography.bodyMedium,
                color = FgSecondary
            )
        }
    }
}

@Composable
private fun Icon(
    @DrawableRes imageId: Int
) {
    Box(
        modifier = Modifier
            .size(68.dp)
            .aspectRatio(1f)
            .background(color = BgHighlight, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painterResource(imageId),
            null,
            modifier = Modifier.padding(10.dp),
            colorFilter = ColorFilter.tint(color = FgActionEmphasis)
        )
    }
}