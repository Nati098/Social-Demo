package ru.social.demo.ui.components.buttons

import androidx.compose.runtime.Composable
import ru.social.demo.R
import ru.social.demo.ui.theme.FgSecondary

@Composable
fun BackButton(
    onClick: () -> Unit,
) {
    CIconButton(
        iconId = R.drawable.ic_arrow_left,
        contentColor = FgSecondary,
        onClick = onClick
    )
}

@Composable
fun ShareButton(
    onClick: () -> Unit,
) {
    CIconButton(
        iconId = R.drawable.ic_share,
        contentColor = FgSecondary,
        onClick = onClick
    )
}

@Composable
fun FilterButton(
    onClick: () -> Unit,
) {
    CIconButton(
        iconId = R.drawable.ic_filter,
        contentColor = FgSecondary,
        onClick = onClick
    )
}

@Composable
fun UserEditButton(
    onClick: () -> Unit,
) {
    CIconButton(
        iconId = R.drawable.ic_user_edit,
        contentColor = FgSecondary,
        onClick = onClick
    )
}
