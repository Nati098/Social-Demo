package ru.social.demo.ui.components.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.social.demo.ui.theme.BgActionPrimary
import ru.social.demo.ui.theme.BgHighlight
import ru.social.demo.ui.theme.BgTertiary
import ru.social.demo.ui.theme.BorderSecondary
import ru.social.demo.ui.theme.FgActionEmphasis
import ru.social.demo.ui.theme.FgActionPrimary
import ru.social.demo.ui.theme.FgPrimary
import ru.social.demo.ui.theme.FgTertiary


@Composable
fun CButton(
    enabled: Boolean = true,
    onClick: () -> Unit,
    iconId: Int? = null,
    label: String? = null
) {
    Button(
        enabled = enabled,
        onClick = onClick,
        shape = RoundedCornerShape(100f)
    ) {
        if (iconId != null)
            Image(painter = painterResource(iconId), null)
        if (label != null)
            Text("$label", style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun CTonalButton(
    enabled: Boolean = true,
    onClick: () -> Unit,
    iconId: Int? = null,
    label: String? = null
) {
    FilledTonalButton (
        enabled = enabled,
        onClick = onClick,
        shape = RoundedCornerShape(100f),
        colors = ButtonColors(
            containerColor = BgHighlight,
            contentColor = FgActionEmphasis,
            disabledContainerColor = BgTertiary,
            disabledContentColor = FgTertiary
        )
    ) {
        if (iconId != null)
            Image(painter = painterResource(iconId), null)
        if (label != null)
            Text("$label", style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun COutlinedButton(
    enabled: Boolean = true,
    onClick: () -> Unit,
    iconId: Int? = null,
    label: String? = null
) {
    OutlinedButton (
        enabled = enabled,
        onClick = onClick,
        shape = RoundedCornerShape(100f)
    ) {
        if (iconId != null)
            Image(painter = painterResource(iconId), null)
        if (label != null)
            Text("$label", style = MaterialTheme.typography.bodyLarge)
    }
}


@Composable
fun CIconButton(
    iconId: Int,
    enabled: Boolean = true,
    size: Dp = 40.dp,
    contentColor: Color = FgPrimary,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    CIconButtonInternal(
        iconId = iconId,
        enabled = enabled,
        size = size,
        bgColor = Color.Transparent,
        borderColor = Color.Transparent,
        contentColor = contentColor,
        modifier = modifier,
        onClick = onClick
    )
}

@Composable
fun CIconButtonFilled(
    iconId: Int,
    enabled: Boolean = true,
    size: Dp = 40.dp,
    bgColor: Color = BgActionPrimary,
    contentColor: Color = FgActionPrimary,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    CIconButtonInternal(
        iconId = iconId,
        enabled = enabled,
        size = size,
        bgColor = bgColor,
        borderColor = bgColor,
        contentColor = contentColor,
        modifier = modifier,
        onClick = onClick
    )
}

@Composable
fun CIconButtonOutlined(
    iconId: Int,
    enabled: Boolean = true,
    size: Dp = 40.dp,
    bgColor: Color = Color.Transparent,
    borderColor: Color = BorderSecondary,
    contentColor: Color = FgPrimary,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    CIconButtonInternal(
        iconId = iconId,
        enabled = enabled,
        size = size,
        bgColor = bgColor,
        borderColor = borderColor,
        contentColor = contentColor,
        modifier = modifier,
        onClick = onClick
    )
}

@Composable
private fun CIconButtonInternal(
    iconId: Int,
    enabled: Boolean = true,
    size: Dp = 40.dp,
    bgColor: Color,
    borderColor: Color,
    contentColor: Color,
    modifier: Modifier,
    onClick: () -> Unit,
) {
    IconButton (
        modifier = modifier
            .border(1.5.dp, borderColor, shape = CircleShape)
            .background(color = bgColor, shape = CircleShape)
            .size(size)
            .padding(size/4),
        enabled = enabled,
        onClick = onClick,
    ) {
        Image(
            painter = painterResource(iconId),
            null,
            modifier = Modifier.fillMaxSize(),
            colorFilter = ColorFilter.tint(color = contentColor)
        )
    }
}