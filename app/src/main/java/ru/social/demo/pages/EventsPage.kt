package ru.social.demo.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import ru.social.demo.R
import ru.social.demo.data.model.TEMP_USER
import ru.social.demo.ui.components.appbars.CAppBar
import ru.social.demo.ui.components.containers.OutlinedContainer

@Composable
fun EventsPage() {

    val listState = rememberLazyListState()
    CAppBar(
        title = stringResource(R.string.events),
        user = TEMP_USER,
        state = listState,
        topBarContent = {
//            val fraction = this.fraction
            Column(
                modifier = Modifier
                    .padding(bottom = 32.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Box(modifier = Modifier.fillMaxWidth().padding(8.dp).height(156.dp)) {
                    Text(
                        text = "Card",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        },
        columnContent = { insets ->
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