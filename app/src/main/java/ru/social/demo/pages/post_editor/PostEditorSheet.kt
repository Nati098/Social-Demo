package ru.social.demo.pages.post_editor

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
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
import ru.social.demo.utils.ImageUtils
import ru.social.demo.utils.NetworkUtils
import ru.social.demo.utils.toBase64

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostEditorSheet(
    post: Post?,
    isBottomSheetVisible: Boolean,
    sheetState: SheetState,
    onDismissRequest: () -> Unit = {}
) {
    Log.d("TEST", "PostEditorSheet starts")
    val context = LocalContext.current

    val isCreateMode = post == null
    var title = remember { mutableStateOf(post?.title ?: "") }
    var text = remember { mutableStateOf(post?.text ?: "") }
    val type = remember { mutableStateOf(post?.type ?: Post.Type.POST) }
    val mediaUrls = remember {
        mutableStateListOf<String>().apply {
            post?.media?.let { media -> addAll(media) }
        }
    }
    val mediaUris = remember {
        mutableStateListOf<Uri>().apply {
            post?.mediaBase64?.let { media ->
                addAll(media.mapNotNull { m -> ImageUtils.base64ToUri(m) })
            }
        }
    }

    fun prepareData() =
        Post(
            createDate = Timestamp.now(),
            type = type.value,
            title = title.value,
            text = text.value,
            media = mediaUrls,
            mediaBase64 = mediaUris.map { it.toBase64(context) }
        )

    fun prepareCopy() =
        post!!.copy(
            updateDate = Timestamp.now(),
            type = type.value,
            title = title.value,
            text = text.value,
            media = mediaUrls,
            mediaBase64 = mediaUris.map { it.toBase64(context) }
        )

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
                    enabled = text.value.isNotEmpty(),
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
                    enabled = text.value.isNotEmpty(),
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
                value = title.value,
                textStyle = SDTheme.typography.headingS,
                singleLine = true,
                onValueChange = { title.value = it }
            )
            CTextField(
                modifier = Modifier.weight(1f),
                value = text.value,
                onValueChange = { text.value = it }
            )

            AttachmentsList(mediaUrls, mediaUris)
            BottomMenu(uris = mediaUris)
        }

    }

}

@Composable
private fun AttachmentsList(urls: MutableList<String>, uris: MutableList<Uri>) {
    LazyRow(
        modifier = Modifier.padding(vertical = 30.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(urls) {
            AttachmentMedia(it) { urls.remove(it) }
        }

        items(uris) {
            AttachmentMedia(it) { uris.remove(it) }
        }
    }
}

@Composable
private fun BottomMenu(uris: MutableList<Uri>) {
    val cameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
//        if (it) {
//          uris.add()
//        }
    }

    val galleryLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetMultipleContents()) {
        uris.addAll(it)
    }

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
            galleryLauncher.launch("image/*")
        }
    }
}