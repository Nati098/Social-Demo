package ru.social.demo.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.social.demo.R
import ru.social.demo.ui.components.appbars.CTopBar
import ru.social.demo.ui.components.buttons.ShareButton
import ru.social.demo.ui.components.buttons.UserEditButton
import ru.social.demo.ui.components.containers.OutlinedContainer

@Composable
fun ProfilePage(
    navController: NavController
) {

    val listState = rememberLazyListState()
    CTopBar(
        title = stringResource(R.string.profile),
        onBack = { navController.navigateUp() },
        actions = {
            UserEditButton(onClick = { })
            ShareButton(onClick = { })
        },
        content = { insets ->
            LazyColumn(
                state = listState,
                contentPadding = insets
            ) {
                items(100) {
                    OutlinedContainer(
                        parentWidth = true,
                        paddingHorizontal = 16.dp,
                        paddingVertical = 16.dp
                    ) {
                        Box(modifier = Modifier.fillMaxWidth().padding(8.dp).height(80.dp)) {
                            Text(
                                text = "Item $it",
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                    HorizontalDivider(thickness = 10.dp, color = Color.Transparent)
                }
            }
        }
    )

}