package ru.social.demo.pages.library

import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.Timestamp
import ru.social.demo.R
import ru.social.demo.base.NavPath
import ru.social.demo.data.model.Post
import ru.social.demo.data.model.User
import ru.social.demo.pages.post_editor.POST
import ru.social.demo.pages.wiki.components.WikiTile
import ru.social.demo.pages.wiki.components.WikiTypeRes
import ru.social.demo.ui.components.ArrowTile
import ru.social.demo.ui.components.Avatar
import ru.social.demo.ui.components.buttons.CButton
import ru.social.demo.ui.components.buttons.CIconButton
import ru.social.demo.ui.components.buttons.CIconButtonOutlined
import ru.social.demo.ui.components.buttons.COutlinedButton
import ru.social.demo.ui.components.buttons.CTextButton
import ru.social.demo.ui.components.buttons.CTonalButton
import ru.social.demo.ui.components.containers.OutlinedContainer
import ru.social.demo.ui.theme.SDTheme

private val TEMP_USER = User(
    id = "0",
    name = "Maria Robbins",
    imageUrl = "https://media.istockphoto.com/id/1326417862/photo/young-woman-laughing-while-relaxing-at-home.jpg?s=612x612&w=0&k=20&c=cd8e6RBGOe4b8a8vTcKW0Jo9JONv1bKSMTKcxaCra8c="
)

private val TEMP_POST1 = Post(
    id = "",
    createDate = Timestamp.now(),
    TEMP_USER,
    title = "Random God",
    text = "tetete",
)


@Composable
fun LibraryPage(
    navController: NavController
) {

    val scrollState = rememberScrollState()
    Scaffold (
        containerColor = SDTheme.colors.bgPrimary
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(it)
                .padding(start = 8.dp, end = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {

            OutlinedContainer(
                parentWidth = true,
                paddingHorizontal = 16.dp,
                paddingVertical = 16.dp
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Avatar",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Avatar(imgUrl = TEMP_USER.imageUrl, char = TEMP_USER.name!![0], size = 96.dp)
                        Avatar(char = 'A', size = 64.dp)
                        Avatar(char = 'B', size = 44.dp, inactive = false)
                        Avatar(imgUrl = TEMP_USER.imageUrl, char = TEMP_USER.name!![0], size = 40.dp, inactive = true)
                    }
                }
            }

            OutlinedContainer(
                parentWidth = true,
                paddingHorizontal = 16.dp,
                paddingVertical = 16.dp
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Buttons",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Row (horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        CButton(label = "Click me!", onClick = { })
                        CButton(label = "Click me!", enabled = false, onClick = { })
                    }
                    Row (horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        CTonalButton(label = "Click me!", onClick = { })
                        CTonalButton(label = "Click me!", enabled = false, onClick = { })
                    }
                    Row (horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        COutlinedButton(label = "Click me!", onClick = { })
                        COutlinedButton(label = "Click me!", enabled = false, onClick = { })
                    }
                    Row (horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        CTextButton(label = "Click me!", onClick = { })
                        CTextButton(label = "Click me!", enabled = false, onClick = { })
                    }
                    Row (horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        CIconButton(iconId = R.drawable.ic_bell, onClick = { })
                        CIconButton(iconId = R.drawable.ic_bell, enabled = false,  contentColor = SDTheme.colors.bgActionPrimary, onClick = { })
                        CIconButtonOutlined(iconId = R.drawable.ic_bell, onClick = { })
                        CIconButtonOutlined(iconId = R.drawable.ic_bell, borderColor = SDTheme.colors.bgActionPrimary, onClick = { })
                        CIconButtonOutlined(iconId = R.drawable.ic_bell, contentColor = SDTheme.colors.bgActionPrimary, onClick = { })
                        CIconButtonOutlined(iconId = R.drawable.ic_bell, onClick = { })
                        CIconButtonOutlined(iconId = R.drawable.ic_bell, bgColor = SDTheme.colors.bgFab, contentColor = SDTheme.colors.fgOnColor, onClick = { })
                    }

                }
            }

            OutlinedContainer(
                parentWidth = true,
                paddingHorizontal = 16.dp,
                paddingVertical = 16.dp
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    ArrowTile(title = "Tile 1", iconId = null)
                    ArrowTile(title = "Tile 2", description = "Description", icon = null)
                    ArrowTile(title = "Tile 3", iconId = R.drawable.ic_user_edit)
                    ArrowTile(
                        title = "Tile 4",
                        description = "Description",
                        icon = {
                            Image(
                                painterResource(R.drawable.book),
                                null,
                                modifier = Modifier
                                    .size(40.dp)
                                    .paint(
                                        painter = painterResource(R.drawable.bg_image_3),
                                        colorFilter = ColorFilter.tint(colorResource(R.color.avatar_blue)),
                                        alpha = 0.3f
                                    )
                            )
                        }
                    )
                }
            }

            WikiTile(type = WikiTypeRes.CHARACTER)

            ArrowTile(
                title = "ModalBottomSheet. New post",
                description = "Click to show. Post = null",
                iconId = R.drawable.ic_calendar,
                onClick = {
                    navController
                        .apply {
                            Bundle().apply {
                                putParcelable(POST, TEMP_POST1)
                            }
                        }
                        .navigate(NavPath.POST_EDITOR)
                }
            )

            ArrowTile(
                title = "ModalBottomSheet. Edit post",
                description = "Click to show. Post = TEMP_POST1",
                iconId = R.drawable.ic_calendar,
                onClick = {
                    navController
                        .apply {
                            Bundle().apply {
                                putParcelable(POST, TEMP_POST1)
                            }
                        }
                        .navigate(NavPath.POST_EDITOR)
                }
            )

        }
    }

}