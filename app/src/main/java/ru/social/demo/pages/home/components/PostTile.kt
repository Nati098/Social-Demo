package ru.social.demo.pages.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import ru.social.demo.data.model.Post
import ru.social.demo.ui.components.containers.OutlinedContainer

@Composable
fun PostTile(
    post: Post
) {
    OutlinedContainer(
        parentWidth = true,
        paddingHorizontal = 16.dp,
        paddingVertical = 16.dp
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            UserBlock(post.user, post.createDate)

            TextBlock(post.title, post.text)

            ReactionBlock(post.reactionCounts, post.commentsCount)
        }
    }
}


