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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.google.firebase.Timestamp
import kotlinx.coroutines.launch
import ru.social.demo.R
import ru.social.demo.data.model.Post
import ru.social.demo.services.FirestoreClient
import ru.social.demo.services.FsPath
import ru.social.demo.ui.components.appbars.CTopBar
import ru.social.demo.ui.components.buttons.CTextButton
import ru.social.demo.ui.components.text.CTextField
import ru.social.demo.ui.theme.SDTheme

const val POST = "post"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostEditorSheet(
    post: Post?,
    modifier: Modifier =  Modifier,
//        .heightIn(max = LocalConfiguration.current.screenHeightDp.dp - 60.dp)
//        .fillMaxHeight(),
    onDismissRequest: () -> Unit = {}
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val coroutineScope = rememberCoroutineScope()

    val isCreateMode = post == null
    var title by remember { mutableStateOf(post?.title ?: "") }
    var text by remember { mutableStateOf(post?.text ?: "") }
    var type by remember { mutableStateOf(Post.Type.POST) }

    fun prepareData() = Post(createDate = Timestamp.now(), type = type, title = title, text = text)
    fun prepareCopy() = post!!.copy(updateDate = Timestamp.now(), type = type, title = title, text = text)

    ModalBottomSheet(
        modifier = modifier,
        sheetState = sheetState,
        onDismissRequest = onDismissRequest,
        dragHandle = { BottomSheetDefaults.DragHandle(color = Color.Transparent) },
        containerColor = Color.Transparent,
        contentWindowInsets = { WindowInsets(0,0,0,0) },
    ) {
        CTopBar(
            title = stringResource((post?.type ?: Post.Type.POST).idString),
            modifier = Modifier
                .statusBarsPadding()
                .navigationBarsPadding()
                .clip(shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)),
            bgColor = SDTheme.colors.bgPrimary,
            topInset = false,
            onBack = { coroutineScope.launch { sheetState.hide() } },
            actions = {
                if (isCreateMode) {
                    CTextButton(
                        label = stringResource(R.string.post_create),
                        enabled = title.isNotBlank() && text.isNotEmpty(),
                        onClick = {
                            coroutineScope
                                .launch { sheetState.hide() }
                                .invokeOnCompletion {
                                    FirestoreClient.getInstance().setData(FsPath.POSTS, prepareData())
                                }
                        }
                    )
                } else {
                    CTextButton(
                        label = stringResource(R.string.post_edit),
                        enabled = title.isNotBlank() && text.isNotEmpty(),
                        onClick = {
                            coroutineScope
                                .launch { sheetState.hide() }
                                .invokeOnCompletion {
                                    FirestoreClient.getInstance().updateData(FsPath.POSTS, prepareCopy())
                                }
                        }
                    )
                }
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
                        value = title,
                        singleLine = true,
                        onValueChange = { title = it }
                    )
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

    LaunchedEffect(Unit) {
        coroutineScope.launch { sheetState.show() }
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