package ru.social.demo.ui.components.buttons.fab

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.social.demo.ui.components.buttons.CIconButtonFilled
import ru.social.demo.ui.theme.BgActionPrimary
import ru.social.demo.ui.theme.BgFab
import ru.social.demo.ui.theme.BgSecondary
import ru.social.demo.ui.theme.FgOnColor

class FabItem(
    val id: String,
    @DrawableRes val iconId: Int,
    @StringRes val label: Int?
) {

    fun onClick() {
        // go to page with parameter postType = id
    }

}

@Composable
fun FabItemButton(item: FabItem) {
    Row(
        modifier = Modifier.wrapContentSize().padding(end = 9.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        item.label?.let {
            Text(
                text = stringResource(it),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(20f))
                    .background(BgSecondary)
                    .padding(4.dp)
            )
        }

        CIconButtonFilled(
            item.iconId,
            bgColor = BgFab,
            contentColor = FgOnColor,
            onClick = { item.onClick() }
        )
    }
}
