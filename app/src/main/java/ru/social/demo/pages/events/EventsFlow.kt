package ru.social.demo.pages.events

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.social.demo.base.NavBarPath
import ru.social.demo.base.NavPath

fun NavGraphBuilder.eventsFlow(
    navController: NavController
) {
    navigation(route = NavBarPath.EVENTS.route, startDestination = NavPath.MAIN) {
        composable(NavPath.MAIN) {
            EventsPage(navController)
        }
    }
}