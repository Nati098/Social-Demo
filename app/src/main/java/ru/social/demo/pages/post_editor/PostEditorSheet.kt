package ru.social.demo.pages.post_editor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import ru.social.demo.R
import ru.social.demo.data.model.Post
import ru.social.demo.ui.components.appbars.CTopBar
import ru.social.demo.ui.components.buttons.CTextButton
import ru.social.demo.ui.components.text.CTextField
import ru.social.demo.ui.theme.SDTheme

enum class PostEditorMode { CREATE, EDIT }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostEditorSheet(
    post: Post,
    mode: PostEditorMode = PostEditorMode.CREATE,
    modifier: Modifier =  Modifier,
    onDismissRequest: () -> Unit = {}
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var text by remember { mutableStateOf(post.text ?: "") }

    ModalBottomSheet(
        modifier = modifier,
        sheetState = sheetState,
        onDismissRequest = onDismissRequest,
        dragHandle = {
            BottomSheetDefaults.DragHandle(color = Color.Transparent)
        },
        containerColor = Color.Transparent,
        contentWindowInsets = { WindowInsets(0,0,0,0) },
    ) {
        CTopBar(
            title = stringResource((post.type ?: Post.TYPE.POST).idString),
            modifier = Modifier.clip(shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)),
            bgColor = SDTheme.colors.bgPrimary,
            topInset = false,
            actions = {
                CTextButton(
                    label = when(mode) {
                        PostEditorMode.CREATE -> stringResource(R.string.post_create)
                        PostEditorMode.EDIT -> stringResource(R.string.post_edit)
                    },
                    enabled = text.isNotEmpty(),
                    onClick = { }
                )
            },
            content = { insets ->
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(
                            top = insets.calculateTopPadding(),
                            bottom = insets.calculateBottomPadding()
                        )
                ) {
                    CTextField(
                        modifier = Modifier
                            .padding(
                                start = insets.calculateStartPadding(LayoutDirection.Ltr),
                                end = insets.calculateEndPadding(LayoutDirection.Ltr)
                            )
                            .weight(1f),
                        value = text,
                        onValueChange = { text = it }
                    )

                    BottomMenu()
                }
            }
        )
    }
}


@Composable
private fun BottomMenu() {
    Box(
        modifier = Modifier
            .height(0.dp)
            .fillMaxWidth()
            .background(color = SDTheme.colors.fgActionEmphasis)
    )
}