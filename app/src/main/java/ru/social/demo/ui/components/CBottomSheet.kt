package ru.social.demo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import ru.social.demo.ui.components.appbars.BSTopBar
import ru.social.demo.ui.theme.SDTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CBottomSheet(
    isBottomSheetVisible: Boolean,
    sheetState: SheetState,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    title: String? = null,
    onBack: (() -> Unit)? = null,
    actions: @Composable (RowScope.() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    if (isBottomSheetVisible) {

        ModalBottomSheet(
            modifier = modifier.fillMaxHeight(),
            sheetState = sheetState,
            onDismissRequest = onDismissRequest,
            dragHandle = null,
            shape = RectangleShape,
            containerColor = Color.Transparent,
            contentWindowInsets = { WindowInsets(0,0,0,0) },
        ) {

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .heightIn(max = LocalConfiguration.current.screenHeightDp.dp)
                    .statusBarsPadding()
                    .navigationBarsPadding()
                    .padding(16.dp)
                    .clip(SDTheme.shapes.corners)
                    .background(color = SDTheme.colors.bgPrimary)
                    .fillMaxWidth()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                BSTopBar(
                    title = title,
                    onBack = onBack,
                    actions = actions
                )
                content(this)
            }
        }
    }
}