package ru.social.demo.ui.components.text

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import ru.social.demo.R
import ru.social.demo.ui.theme.SDTheme
import ru.social.demo.utils.verticalScrollbar

@Composable
fun CTextField(
    modifier: Modifier = Modifier,
    value: String,
    singleLine: Boolean = false,
    onValueChange: (String) -> Unit,
) {
    CompositionLocalProvider(
        LocalTextSelectionColors provides TextSelectionColors(
            handleColor = SDTheme.colors.fgPrimary,
            backgroundColor = SDTheme.colors.fgActionEmphasis.copy(alpha = 0.3f)
        )
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier,
            singleLine = singleLine,
            decorationBox = { innerTextField ->
                val scrollState = rememberScrollState()
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScrollbar(scrollState, color = SDTheme.colors.fgActionEmphasis)
                        .verticalScroll(state = scrollState)
                ) {
                    if (value.isEmpty())
                        Text(
                            stringResource(R.string.post_create_placeholder),
                            color = SDTheme.colors.fgTertiary
                        )
                    innerTextField()
                }

            },
            textStyle = SDTheme.typography.bookL.copy(
                color = SDTheme.colors.fgPrimary
            ),
            cursorBrush = SolidColor(SDTheme.colors.fgPrimary)
        )
    }
}
