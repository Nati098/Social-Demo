package ru.social.demo.pages.library

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.social.demo.base.NavBarPath
import ru.social.demo.base.NavPath

fun NavGraphBuilder.libraryFlow() {
    navigation(route = NavBarPath.LIBRARY.route, startDestination = NavPath.main) {
        composable(NavPath.main) {
            LibraryPage()
        }
    }
}