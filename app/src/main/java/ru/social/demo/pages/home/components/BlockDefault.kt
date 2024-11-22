package ru.social.demo.pages.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.firebase.Timestamp
import ru.social.demo.R
import ru.social.demo.data.model.User
import ru.social.demo.utils.parseDate
import ru.social.demo.ui.components.Avatar
import ru.social.demo.ui.components.ExpandableText
import ru.social.demo.ui.components.buttons.CIconButton
import ru.social.demo.ui.theme.FgPrimary
import ru.social.demo.ui.theme.FgSecondary
import ru.social.demo.ui.theme.FgTertiary

@Composable
fun UserBlock(user: User?, createDate: Timestamp?) {
    Row(
//        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Avatar(
            char = user?.name?.get(0) ?: 'U',
            imgUrl = user?.imageUrl,
            size = 36.dp
        )

        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                user?.name ?: "",
                style = MaterialTheme.typography.bodyMedium,
                color = FgPrimary
            )
            createDate?.let {
                Text(
                    it.parseDate(),
                    style = MaterialTheme.typography.bodySmall,
                    color = FgTertiary
                )
            }

        }

        Spacer(modifier = Modifier.weight(1f))
        CIconButton(
            iconId = R.drawable.ic_more,
            contentColor = FgSecondary,
            size = 30.dp,
        ) {
            // TODO: post menu!
        }
    }
}

@Composable
fun TextBlock(title: String?, body: String?) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        title?.let {
            Text(
                it,
                style = MaterialTheme.typography.headlineSmall,
                color = FgPrimary,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }
        body?.let {
            ExpandableText(
                text = it,
                style = MaterialTheme.typography.labelLarge,
                color = FgSecondary,
                showMoreStyle = SpanStyle(color = FgPrimary, fontWeight = FontWeight.W500)
            )
        }

    }
}

@Composable
fun ReactionBlock(reactionsCount: Int?, commentsCount: Int?) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Counter(
            image = {
                Image(
                    painterResource(R.drawable.ic_heart),
                    null,
                    colorFilter = ColorFilter.tint(color = FgTertiary)
                )
            },
            reactionsCount ?: 0
        )

        Counter(
            image = {
                Image(
                    painterResource(R.drawable.ic_chat),
                    null,
                    colorFilter = ColorFilter.tint(color = FgTertiary)
                )
            },
            commentsCount ?: 0
        )
    }
}

@Composable
private fun Counter(
    image: @Composable () -> Unit,
    counter: Int
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        image.invoke()
        Spacer(Modifier.width(4.dp))
        Text(
            "$counter",
            style = MaterialTheme.typography.bodySmall,
            color = FgTertiary
        )

    }
}