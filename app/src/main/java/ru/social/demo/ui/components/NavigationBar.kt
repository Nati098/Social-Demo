package ru.social.demo.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.NavigationRailItemColors
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteItemColors
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
import ru.social.demo.ui.theme.SDTheme

enum class NavPath(val label: Int, val idActive: Int, val idInactive: Int) {
    LIBRARY(R.string.library, R.drawable.wiki_filled, R.drawable.wiki),
    WIKI(R.string.wiki, R.drawable.wiki_filled, R.drawable.wiki),
    MAIN(R.string.main, R.drawable.main_filled, R.drawable.main),
    EVENTS(R.string.events, R.drawable.events_filled, R.drawable.events),
    PROFILE(R.string.profile, R.drawable.profile_filled, R.drawable.profile)
}

@Composable
fun NavigationBar() {
    var currentPath by rememberSaveable { mutableStateOf(NavPath.LIBRARY) }

    val unselectedColor = SDTheme.colors.fgPrimary.copy(alpha = 0.25f)
    val barItemColors = NavigationBarItemColors(
        selectedIndicatorColor = Color.Transparent,
        selectedIconColor = Color.Unspecified,
        selectedTextColor = Color.Unspecified,
        unselectedIconColor = unselectedColor,
        unselectedTextColor = unselectedColor,
        disabledIconColor = unselectedColor,
        disabledTextColor = unselectedColor
    )
    val railItemColors = NavigationRailItemColors(
        selectedIndicatorColor = Color.Transparent,
        selectedIconColor = Color.Unspecified,
        selectedTextColor = Color.Unspecified,
        unselectedIconColor = unselectedColor,
        unselectedTextColor = unselectedColor,
        disabledIconColor = unselectedColor,
        disabledTextColor = unselectedColor
    )
    val drawerItemColors = NavigationDrawerItemDefaults.colors(
        selectedContainerColor = Color.Transparent,
        unselectedContainerColor = Color.Transparent,
        selectedIconColor = Color.Unspecified,
        selectedTextColor = Color.Unspecified,
        selectedBadgeColor = Color.Unspecified,
        unselectedIconColor = unselectedColor,
        unselectedTextColor = unselectedColor,
        unselectedBadgeColor = unselectedColor
    )

    NavigationSuiteScaffold(
        modifier = Modifier.fillMaxSize(),
        navigationSuiteColors = NavigationSuiteDefaults.colors(
            navigationBarContainerColor = SDTheme.colors.bgPrimary,
            navigationBarContentColor = SDTheme.colors.fgPrimary,
            navigationRailContainerColor = SDTheme.colors.bgPrimary,
            navigationRailContentColor = SDTheme.colors.fgPrimary,
            navigationDrawerContainerColor = SDTheme.colors.bgPrimary,
            navigationDrawerContentColor = SDTheme.colors.fgPrimary
        ),
        navigationSuiteItems = {
            NavPath.entries.forEach {
                item(
                    icon = { Icon(
                        painter = painterResource(if (it == currentPath) it.idActive else it.idInactive),
                        contentDescription = null,
                        tint = SDTheme.colors.fgPrimary.copy(alpha = if (it == currentPath) 1f else 0.25f)
                    ) },
                    label = { Text(
                        stringResource(it.label),
                        color = SDTheme.colors.fgPrimary.copy(alpha = if (it == currentPath) 1f else 0.25f),
                        style = SDTheme.tyrography.bodyMediumS
                    ) },
                    selected = it == currentPath,
                    colors = NavigationSuiteItemColors(
                        navigationBarItemColors = barItemColors,
                        navigationRailItemColors = railItemColors,
                        navigationDrawerItemColors = drawerItemColors
                    ),
                    onClick = { currentPath = it }
                )
            }
        }
    ) {

        when(currentPath) {
            NavPath.MAIN -> HomePage()
            NavPath.LIBRARY -> LibraryPage()
            NavPath.WIKI -> WikiPage()
            NavPath.EVENTS -> EventsPage()
            NavPath.PROFILE -> ProfilePage()
        }

    }
}