package ru.social.demo.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import ru.social.demo.R
import ru.social.demo.ui.theme.BgAvatarBlue
import ru.social.demo.ui.theme.BgAvatarGreen
import ru.social.demo.ui.theme.BgAvatarPurple
import ru.social.demo.ui.theme.CWhite
import ru.social.demo.ui.theme.FilterColor
import ru.social.demo.ui.theme.StatusGreen
import ru.social.demo.ui.theme.StatusGrey

enum class AvatarRes(val color: Color, val placeholder: Int) {
    BLUE(BgAvatarBlue, R.drawable.bg_image_6),
    PURPLE(BgAvatarPurple, R.drawable.bg_image_3),
    GREEN(BgAvatarGreen, R.drawable.bg_image_9)
}

@Composable
fun Avatar(
    size: Dp,
    imgUrl: String? = null,
    char: Char,
    inactive: Boolean? = null
) {
    Box(
        modifier = Modifier
            .size(size)
            .graphicsLayer {
                compositingStrategy = CompositingStrategy.Offscreen
                clip = true
            }
            .drawWithCache {
                val path = Path()
                path.addOval(
                    Rect(
                        topLeft = Offset.Zero,
                        bottomRight = Offset(this.size.width, this.size.height)
                    )
                )

                onDrawWithContent {
                    clipPath(path) { this@onDrawWithContent.drawContent() }

                    if (inactive != null) {
                        val dotSize = this.size.width / 6f
                        drawCircle(
                            Color.Black,
                            radius = dotSize,
                            center = Offset(
                                x = this.size.width - dotSize,
                                y = this.size.height - dotSize
                            ),
                            blendMode = BlendMode.Clear
                        )
                        drawCircle(
                            if (inactive) StatusGrey else StatusGreen,
                            radius = 0.8f * dotSize,
                            center = Offset(
                                x = this.size.width - dotSize,
                                y = this.size.height - dotSize
                            )
                        )
                    }
                }
            }
    ) {

        if (imgUrl != null) {
            AsyncImage(
                model = imgUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        } else {
            Placeholder(size, char)
        }

    }
}

@Composable
private fun Placeholder(size: Dp, char: Char) {
    val resources = AvatarRes.entries[char.hashCode() % 3]

    Box {
        Image(
            painter = painterResource(resources.placeholder),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .background(color = resources.color)
                .offset(x = -size/8, y = size/6),
            colorFilter = ColorFilter.tint(FilterColor)
        )
        Text(
            "$char",
            fontWeight = FontWeight.ExtraBold,
            fontSize = (size.value/3).sp,
            textAlign = TextAlign.Center,
            color = CWhite,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}