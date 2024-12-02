package ru.social.demo.pages.wiki

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.social.demo.base.NavBarPath
import ru.social.demo.base.NavPath

fun NavGraphBuilder.wikiFlow(
    navController: NavController
) {
    navigation(route = NavBarPath.WIKI.route, startDestination = NavPath.MAIN) {
        composable(NavPath.MAIN) {
            WikiPage(navController)
        }
    }
}