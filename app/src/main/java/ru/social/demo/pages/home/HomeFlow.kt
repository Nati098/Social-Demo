package ru.social.demo.pages.home

import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.social.demo.base.NavBarPath
import ru.social.demo.base.NavPath
import ru.social.demo.pages.ProfilePage


fun NavGraphBuilder.homeFlow(
    navController: NavController
) {
    navigation(route = NavBarPath.HOME.route, startDestination = NavPath.main) {
        composable(NavPath.main) { navBackStackEntry ->
            val parentEntry = remember(navBackStackEntry) { navController.getBackStackEntry(NavPath.main) }
            val homeViewModel = hiltViewModel<HomeViewModel>(parentEntry)
            HomePage(navController, homeViewModel)
        }
        composable(NavPath.profile) {
            ProfilePage(
                navigateBack = { navController.navigateUp() }
            )
        }
    }
}