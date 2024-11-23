package ru.social.demo.ui.components.appbars.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.social.demo.ui.theme.SDTheme

@Composable
fun CustomScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable CustomScaffoldTopBarScope.() -> Unit = {},
    content: @Composable (insets: PaddingValues) -> Unit
) {
    CustomScaffoldInternal(
        modifier = modifier,
        topBar = topBar,
        content = content
    )
}

@Composable
private fun CustomScaffoldInternal(
    modifier: Modifier = Modifier,
    topBar: @Composable CustomScaffoldTopBarScope.() -> Unit = {},
    content: @Composable (insets: PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier,
        containerColor = Color.Transparent
    ) { insets ->
        Box (
            Modifier.background(color = SDTheme.colors.bgPrimary)
        ) {
            Box(
                modifier = Modifier.padding(top = CollapsibleTopAppBarDefaults.minHeight)
            ) {
                content(
                    PaddingValues(
                        top = insets.calculateTopPadding() + 20.dp,
                        start = 20.dp,
                        end = 20.dp,
                        bottom = 20.dp
                    )
                )
            }
            CompositionLocalProvider(
                TopAppBarInsets provides insets
            ) {
                CustomScaffoldTopBarScope.topBar()
            }
        }
    }
}