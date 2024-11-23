package ru.social.demo.ui.components.buttons

import androidx.compose.runtime.Composable
import ru.social.demo.R
import ru.social.demo.ui.theme.SDTheme

@Composable
fun BackButton(
    onClick: () -> Unit,
) {
    CIconButton(
        iconId = R.drawable.ic_arrow_left,
        contentColor = SDTheme.colors.fgSecondary,
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
