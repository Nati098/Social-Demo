package ru.social.demo.pages.home

import androidx.compose.material.navigation.bottomSheet
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import ru.social.demo.base.NavBarPath
import ru.social.demo.base.NavPath
import ru.social.demo.data.model.Post
import ru.social.demo.data.model.PostCNavType
import ru.social.demo.pages.profile.ProfilePage
import ru.social.demo.pages.post_editor.POST
import ru.social.demo.pages.post_editor.PostEditorSheet


fun NavGraphBuilder.homeFlow(
    navController: NavController
) {
    navigation(route = NavBarPath.HOME.route, startDestination = NavPath.MAIN) {
        composable(NavPath.MAIN) {
            val parentEntry = remember(it) { navController.getBackStackEntry(NavPath.MAIN) }
            val homeViewModel = hiltViewModel<HomeViewModel>(parentEntry)
            HomePage(navController, homeViewModel)
        }
        composable(NavPath.PROFILE) {
            ProfilePage(
                navigateBack = { navController.navigateUp() }
            )
        }
        bottomSheet(
            NavPath.POST_EDITOR,
            arguments = listOf(
                navArgument(POST) {
                    type = PostCNavType()
                    nullable = true
                    defaultValue = null
                }
            )
        ) {
            PostEditorSheet(post = it.arguments?.getParcelable<Post>(POST))
        }
    }
}