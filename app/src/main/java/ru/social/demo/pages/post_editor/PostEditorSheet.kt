package ru.social.demo.pages.post_editor

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.firebase.Timestamp
import ru.social.demo.R
import ru.social.demo.data.model.Post
import ru.social.demo.services.FirestoreClient
import ru.social.demo.services.FsPath
import ru.social.demo.ui.components.CBottomSheet
import ru.social.demo.ui.components.buttons.CIconButton
import ru.social.demo.ui.components.buttons.CTextButton
import ru.social.demo.ui.components.text.CTextField
import ru.social.demo.ui.theme.SDTheme
import ru.social.demo.utils.NetworkUtils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostEditorSheet(
    post: Post?,
    isBottomSheetVisible: Boolean,
    sheetState: SheetState,
    onDismissRequest: () -> Unit = {}
) {
    Log.d("TEST", "PostEditorSheet starts")
    val isCreateMode = post == null
    var title by remember { mutableStateOf(post?.title ?: "") }
    var text by remember { mutableStateOf(post?.text ?: "") }
    var type by remember { mutableStateOf(post?.type ?: Post.Type.POST) }

    fun prepareData() = Post(createDate = Timestamp.now(), type = type, title = title, text = text)
    fun prepareCopy() = post!!.copy(updateDate = Timestamp.now(), type = type, title = title, text = text)

    CBottomSheet(
        isBottomSheetVisible = isBottomSheetVisible,
        sheetState = sheetState,
        onDismissRequest = onDismissRequest,
        title = stringResource((post?.type ?: Post.Type.POST).idString),
        onBack = onDismissRequest,
        actions = {
            if (isCreateMode) {
                CTextButton(
                    label = stringResource(R.string.post_create),
                    enabled = text.isNotEmpty(),
                    onClick = {
                        onDismissRequest()
                        NetworkUtils.makeCallIO {
                            FirestoreClient.getInstance().setData(FsPath.POSTS, prepareData())
                        }
                    }
                )
            } else {
                CTextButton(
                    label = stringResource(R.string.post_edit),
                    enabled = text.isNotEmpty(),
                    onClick = {
                        onDismissRequest()
                        NetworkUtils.makeCallIO {
                            FirestoreClient.getInstance().updateData(FsPath.POSTS, prepareCopy())
                        }
                    }
                )
            }
        }
    ) {
        CTextField(
            value = title,
            textStyle = SDTheme.typography.headingS,
            singleLine = true,
            onValueChange = { title = it }
        )
        CTextField(
            modifier = Modifier.fillMaxHeight(),
            value = text,
            onValueChange = { text = it }
        )

        BottomMenu()

    }

}

@Composable
private fun BottomMenu() {
    Row(
        modifier = Modifier
            .background(color = SDTheme.colors.fgActionEmphasis)
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        CIconButton(
            size = 20.dp,
            iconId = R.drawable.ic_camera_plus,
            contentColor = SDTheme.colors.fgSecondary
        ) { }
        CIconButton(
            size = 20.dp,
            iconId = R.drawable.ic_image,
            contentColor = SDTheme.colors.fgSecondary
        ) { }
    }
}