package ru.social.demo.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import ru.social.demo.R
import ru.social.demo.pages.EventsPage
import ru.social.demo.pages.home.HomePage
import ru.social.demo.pages.LibraryPage
import ru.social.demo.pages.ProfilePage
import ru.social.demo.pages.wiki.WikiPage
import ru.social.demo.ui.theme.FgPrimary

enum class NavPath(val label: Int, val idActive: Int, val idInactive: Int) {
    LIBRARY(R.string.nav_library, R.drawable.wiki_filled, R.drawable.wiki),
    WIKI(R.string.nav_wiki, R.drawable.wiki_filled, R.drawable.wiki),
    MAIN(R.string.nav_main, R.drawable.main_filled, R.drawable.main),
    EVENTS(R.string.nav_events, R.drawable.events_filled, R.drawable.events),
    PROFILE(R.string.nav_profile, R.drawable.profile_filled, R.drawable.profile)
}

@Composable
fun NavigationBar() {
    var currentPath by rememberSaveable { mutableStateOf(NavPath.LIBRARY) }

    NavigationSuiteScaffold(
        modifier = Modifier.fillMaxSize(),
        navigationSuiteItems = {
            NavPath.entries.forEach {
                item(
                    icon = { Icon(
                        painter = painterResource(if (it == currentPath) it.idActive else it.idInactive),
                        contentDescription = null,
                        tint = if (it == currentPath) Color.Unspecified else FgPrimary.copy(alpha = 0.25f)
                    ) },
                    label = { Text(
                        stringResource(it.label),
                        color = if (it == currentPath) Color.Unspecified else FgPrimary.copy(alpha = 0.25f),
                        style = MaterialTheme.typography.bodySmall
                    ) },
                    selected = it == currentPath,
                    onClick = { currentPath = it }
                )
            }
        }
    ) {
//        Scaffold {
//            Box(modifier = Modifier.padding(it).fillMaxSize()) {
//
//            }
//        }

        when(currentPath) {
            NavPath.MAIN -> HomePage()
            NavPath.LIBRARY -> LibraryPage()
            NavPath.WIKI -> WikiPage()
            NavPath.EVENTS -> EventsPage()
            NavPath.PROFILE -> ProfilePage()
        }

    }
}