package ru.social.demo.ui.components.buttons

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.social.demo.R
import ru.social.demo.ui.theme.SDTheme

@Composable
fun BackButton(
    onClick: () -> Unit,
) {
    CIconButton(
        iconId = R.drawable.ic_arrow_left,
        contentColor = SDTheme.colors.fgSecondary,
        size = 44.dp,
        onClick = onClick
    )
}

@Composable
fun ShareButton(
    onClick: () -> Unit,
) {
    CIconButton(
        iconId = R.drawable.ic_share,
        contentColor = SDTheme.colors.fgSecondary,
        onClick = onClick
    )
}

@Composable
fun FilterButton(
    onClick: () -> Unit,
) {
    CIconButton(
        iconId = R.drawable.ic_filter,
        contentColor = SDTheme.colors.fgSecondary,
        onClick = onClick
    )
}

@Composable
fun UserEditButton(
    onClick: () -> Unit,
) {
    CIconButton(
        iconId = R.drawable.ic_user_edit,
        contentColor = SDTheme.colors.fgSecondary,
        onClick = onClick
    )
}


@Composable
fun CreateButton(
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    CTextButton(
        label = stringResource(R.string.post_create),
        enabled = enabled,
        onClick = onClick
    )
}