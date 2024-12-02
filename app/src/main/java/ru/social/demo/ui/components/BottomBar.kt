package ru.social.demo.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.navigation.rememberBottomSheetNavigator
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ru.social.demo.base.AppState
import ru.social.demo.base.NavBarPath
import ru.social.demo.pages.events.eventsFlow
import ru.social.demo.pages.home.homeFlow
import ru.social.demo.pages.library.libraryFlow
import ru.social.demo.pages.wiki.wikiFlow
import ru.social.demo.ui.theme.SDTheme
import ru.social.demo.utils.topBorder

@Composable
fun BottomBar() {
    val bsNavigator = rememberBottomSheetNavigator()
    val navController = rememberNavController(bsNavigator)

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
        containerColor = SDTheme.colors.bgPrimary,
        bottomBar = {
            if (appState.isBottomBarVisible) {
                NavigationBar (
                    modifier = Modifier.topBorder(width = 1.dp, color = SDTheme.colors.borderColor),
                    containerColor = SDTheme.colors.bgPrimary,
                    contentColor = SDTheme.colors.fgPrimary,
                    tonalElevation = 100.dp
                ) {
                    NavBarPath.entries.forEach {
                        val isSelected = it.route == appState.parentRoute
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
        Box(
            modifier = Modifier.padding(
                start = insets.calculateStartPadding(LayoutDirection.Ltr),
                end = insets.calculateEndPadding(LayoutDirection.Ltr),
                bottom = insets.calculateBottomPadding()
            )
        ) {
            NavHost(
                navController = navController,
                startDestination = NavBarPath.LIBRARY.route
            ) {

                homeFlow(navController)
                libraryFlow(navController)
                wikiFlow(navController)
                eventsFlow()

            }
        }
    }
}
