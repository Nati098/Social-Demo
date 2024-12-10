package ru.social.demo.pages.post_editor.components

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.placeholder
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
                .placeholder(R.drawable.img_placeholder)
                .data(uri)
                .build(),
            contentDescription = uri.path,
            contentScale = ContentScale.Crop,
            error = painterResource(R.drawable.img_placeholder)
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
            contentScale = ContentScale.Crop,
            error = painterResource(R.drawable.img_placeholder)
        )
    }
}

@Composable
fun AttachmentVideo(
    url: String,
    onClose: () -> Unit
) {
    AttachmentInternal(
        onClose = onClose,
//        desc = time
    ) {
        AsyncImage(
            model = url,
            contentDescription = url,
            contentScale = ContentScale.Crop,
            error = painterResource(R.drawable.img_placeholder)
        )
    }
}

@Composable
private fun AttachmentInternal(
    onClose: () -> Unit,
    desc: String? = null,
    image: @Composable () -> Unit
) {
    DefaultRoundedContainer(
        modifier = Modifier.size(84.dp).aspectRatio(1f)
    ) {
        image()

        Box(Modifier.padding(6.dp).fillMaxSize()) {
            CIconButtonFilled (
                modifier = Modifier.align(Alignment.TopEnd),
                iconId = R.drawable.ic_close,
                size = 16.dp,
                bgColor = SDTheme.colors.fgSecondary,
                contentColor = SDTheme.colors.fgOnColor,
                onClick = onClose
            )

            desc?.let {
                Text(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .wrapContentSize()
                        .clip(SDTheme.shapes.buttonCorners)
                        .background(color = SDTheme.colors.fgSecondary),
                    text = desc,
                    style = SDTheme.typography.bodyBoldS,
                    color = SDTheme.colors.fgOnColor
                )
            }
        }
    }
}
