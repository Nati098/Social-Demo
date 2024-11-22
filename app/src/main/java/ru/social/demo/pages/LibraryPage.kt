package ru.social.demo.pages

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.social.demo.R
import ru.social.demo.data.model.User
import ru.social.demo.pages.wiki.components.WikiTile
import ru.social.demo.pages.wiki.components.WikiTypeRes
import ru.social.demo.ui.components.ArrowTile
import ru.social.demo.ui.components.Avatar
import ru.social.demo.ui.components.buttons.CButton
import ru.social.demo.ui.components.buttons.CIconButton
import ru.social.demo.ui.components.buttons.CIconButtonOutlined
import ru.social.demo.ui.components.buttons.COutlinedButton
import ru.social.demo.ui.components.buttons.CTonalButton
import ru.social.demo.ui.components.containers.OutlinedContainer
import ru.social.demo.ui.theme.BgActionPrimary
import ru.social.demo.ui.theme.BgAvatarBlue
import ru.social.demo.ui.theme.BgFab
import ru.social.demo.ui.theme.FgOnColor

@Composable
fun LibraryPage() {

    val user = User(
        id = 0,
        imageUrl = "https://media.istockphoto.com/id/1326417862/photo/young-woman-laughing-while-relaxing-at-home.jpg?s=612x612&w=0&k=20&c=cd8e6RBGOe4b8a8vTcKW0Jo9JONv1bKSMTKcxaCra8c=",
        name = "Maria"
    )

    val scrollState = rememberScrollState()
    Scaffold {
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
                    Row {
                        Avatar(imgUrl = user.imageUrl, char = user.name!![0], size = 96.dp)
                        Avatar(char = 'A', size = 64.dp)
                        Avatar(char = 'B', size = 44.dp, inactive = false)
                        Avatar(imgUrl = user.imageUrl, char = user.name!![0], size = 40.dp, inactive = true)
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
                    CButton(label = "Click me!", onClick = { })
                    CTonalButton(label = "Click me!", onClick = { })
                    COutlinedButton(label = "Click me!", onClick = { })
                    Row {
                        CIconButton(iconId = R.drawable.ic_bell, onClick = { })
                        CIconButton(iconId = R.drawable.ic_bell, enabled = false,  contentColor = BgActionPrimary, onClick = { })
                        CIconButtonOutlined(iconId = R.drawable.ic_bell, onClick = { })
                        CIconButtonOutlined(iconId = R.drawable.ic_bell, borderColor = BgActionPrimary, onClick = { })
                        CIconButtonOutlined(iconId = R.drawable.ic_bell, contentColor = BgActionPrimary, onClick = { })
                        CIconButtonOutlined(iconId = R.drawable.ic_bell, onClick = { })
                        CIconButtonOutlined(iconId = R.drawable.ic_bell, bgColor = BgFab, contentColor = FgOnColor, onClick = { })
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
                                        colorFilter = ColorFilter.tint(BgAvatarBlue),
                                        alpha = 0.3f
                                    )
                            )
                        }
                    )
                }
            }

            WikiTile(type = WikiTypeRes.CHARACTER)

        }
    }

}
