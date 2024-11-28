package ru.social.demo.ui.components

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.NavigationRailItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.social.demo.base.AppState
import ru.social.demo.base.NavBarPath
import ru.social.demo.base.NavPath
import ru.social.demo.pages.EventsPage
import ru.social.demo.pages.home.HomePage
import ru.social.demo.pages.LibraryPage
import ru.social.demo.pages.ProfilePage
import ru.social.demo.pages.wiki.WikiPage
import ru.social.demo.ui.theme.SDTheme

@Preview
@Composable
fun BottomBar() {
    val navController = rememberNavController()
    val appState = remember(rememberNavController()) { AppState(navController) }

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

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (appState.isBottomBarVisible) {
                NavigationBar (
                    containerColor = SDTheme.colors.bgPrimary,
                    contentColor = SDTheme.colors.fgPrimary,
                    tonalElevation = 8.dp
                ) {
                    NavBarPath.entries.forEach {
                        val isSelected = it.route == appState.currentRoute
                        NavigationBarItem(
                            icon = { Icon(
                                painterResource(if (isSelected) it.idActive else it.idInactive),
                                stringResource(it.label)
                            ) },
                            label = { Text(stringResource(it.label), style = SDTheme.typography.bodyMediumS) },
                            colors = barItemColors,
                            selected = isSelected,
                            onClick = { appState.navigateToBottomBarTab(it.route) }
                        )
                    }
                }
            }
        }
    ) { insets ->
        Log.d("TEST", "Main insets = $insets")
        NavHost(
            navController = navController,
            startDestination = NavPath.library
        ) {

            composable(NavPath.main) { HomePage(navController) }
            composable(NavPath.library) { LibraryPage() }
            composable(NavPath.wiki) { WikiPage(navController) }
            composable(NavPath.events) { EventsPage() }

            composable(NavPath.profile) { ProfilePage(navController) }

        }
    }
}
