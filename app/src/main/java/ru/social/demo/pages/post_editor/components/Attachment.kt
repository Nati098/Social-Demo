package ru.social.demo.pages.post_editor.components

import android.net.Uri
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import ru.social.demo.R
import ru.social.demo.ui.components.buttons.CIconButtonFilled
import ru.social.demo.ui.components.containers.DefaultRoundedContainer
import ru.social.demo.ui.theme.SDTheme

@Composable
fun AttachmentMedia(
    uri: Uri,
    onClose: () -> Unit
) {
    AttachmentInternal(
        onClose = onClose
    ) {
        AsyncImage(
            model = ImageRequest
                .Builder(LocalContext.current)
                .data(uri)
                .build(),
            contentDescription = uri.path,
        )
    }
}

@Composable
fun AttachmentMedia(
    url: String,
    onClose: () -> Unit
) {
    AttachmentInternal(
        onClose = onClose
    ) {
        AsyncImage(
            model = url,
            contentDescription = url,
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
private fun AttachmentInternal(
    onClose: () -> Unit,
    image: @Composable () -> Unit
) {
    DefaultRoundedContainer(
        modifier = Modifier.size(84.dp).aspectRatio(1f)
    ) {
        image()
        CIconButtonFilled (
            iconId = R.drawable.ic_close,
            size = 10.dp,
            bgColor = SDTheme.colors.bgHighlight,
            contentColor = SDTheme.colors.fgOnColor,
            onClick = onClose
        )
    }
}
