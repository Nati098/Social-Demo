package ru.social.demo.pages.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.social.demo.R
import ru.social.demo.data.model.User
import ru.social.demo.ui.components.CIcon
import ru.social.demo.ui.theme.SDTheme
import ru.social.demo.utils.calculateAge
import ru.social.demo.utils.parseBirthdayDate

@Composable
fun UserInfoBlock(
    user: User? = null
) {
    InfoBlock(title = stringResource(R.string.details)) {
        DetailsDataItem(
            iconId = R.drawable.ic_cake,
            { Text(
                user?.birthday?.parseBirthdayDate() ?: "",
                style = SDTheme.typography.bodyMediumL,
                color = SDTheme.colors.fgPrimary
            ) },
            { Text(
                user?.birthday?.let { "•" } ?: "",
                style = SDTheme.typography.bodyMediumL,
                color = SDTheme.colors.fgTertiary
            ) },
            { Text (
                "${user?.birthday?.calculateAge()} years",
                style = SDTheme.typography.bodyMediumL,
                color = SDTheme.colors.fgSecondary

            ) }   // TODO: word depends on number!
        )
        DetailsDataItem(
            iconId = user?.gender?.iconId ?: R.drawable.ic_gender_male,
            { Text(
                String.format(
                    stringResource(R.string.gender),
                    user?.gender?.stringId?.let { stringResource(it) } ?: ""
                ),
                style = SDTheme.typography.bodyMediumL,
                color = SDTheme.colors.fgPrimary
            ) }
        )
    }
}

@Composable
private fun DetailsDataItem(
    iconId: Int,
    vararg textBlocks: @Composable () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        CIcon(
            size = 24.dp,
            bgColor = SDTheme.colors.bgSecondaryLight,
            imageId = iconId,
            imageColor = SDTheme.colors.fgPrimary,
            padding = 6.dp
        )
        textBlocks.forEach { it.invoke() }
    }
}
