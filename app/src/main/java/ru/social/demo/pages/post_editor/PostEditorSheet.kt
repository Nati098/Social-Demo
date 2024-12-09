package ru.social.demo.pages.post_editor

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.firebase.Timestamp
import ru.social.demo.R
import ru.social.demo.data.model.Post
import ru.social.demo.pages.post_editor.components.AttachmentMedia
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
    val padding = 24.dp

    val isCreateMode = post == null
    var title by remember { mutableStateOf(post?.title ?: "") }
    var text by remember { mutableStateOf(post?.text ?: "") }
    var type by remember { mutableStateOf(post?.type ?: Post.Type.POST) }
    var mediaUrls by remember { mutableStateOf(post?.media ?: listOf()) }
    var mediaUris by remember { mutableStateOf(listOf<Uri>()) }

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
                            FirestoreClient.getInstance()
                                .setData(FsPath.POSTS, prepareData())
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
                            FirestoreClient.getInstance()
                                .updateData(FsPath.POSTS, prepareCopy())
                        }
                    }
                )
            }
        }
    ) {

        Column {
            CTextField(
                value = title,
                textStyle = SDTheme.typography.headingS,
                singleLine = true,
                onValueChange = { title = it }
            )
            CTextField(
                modifier = Modifier.weight(1f),
                value = text,
                onValueChange = { text = it }
            )

            AttachmentsList(mediaUrls, mediaUris)
            BottomMenu()
        }

    }

}

@Composable
private fun AttachmentsList(urls: List<String>, uris: List<Uri>) {
    LazyRow(
        modifier = Modifier.padding(vertical = 30.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(urls) {
            AttachmentMedia(it) { }
        }

        items(uris) {
            AttachmentMedia(it) { }
        }
    }
}

@Composable
private fun BottomMenu() {
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
    ) {
        CIconButton(
            size = 44.dp,
            iconId = R.drawable.ic_camera_plus,
            contentColor = SDTheme.colors.fgSecondary
        ) {

        }

        CIconButton(
            size = 44.dp,
            iconId = R.drawable.ic_image,
            contentColor = SDTheme.colors.fgSecondary
        ) {

        }
    }
}