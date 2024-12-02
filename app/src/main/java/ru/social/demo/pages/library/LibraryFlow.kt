package ru.social.demo.pages.library

import androidx.compose.material.navigation.bottomSheet
import androidx.compose.material3.Text
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import ru.social.demo.base.NavBarPath
import ru.social.demo.base.NavPath
import ru.social.demo.data.model.Post
import ru.social.demo.data.model.PostCNavType
import ru.social.demo.pages.post_editor.POST
import ru.social.demo.pages.post_editor.PostEditorSheet

fun NavGraphBuilder.libraryFlow(
    navController: NavController
) {
    navigation(route = NavBarPath.LIBRARY.route, startDestination = NavPath.MAIN) {

        composable(NavPath.MAIN) {
            LibraryPage(navController)
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
            Text("This is a cool bottom sheet!")
        }
    }
}