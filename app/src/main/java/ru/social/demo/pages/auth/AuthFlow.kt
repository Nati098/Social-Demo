package ru.social.demo.pages.auth

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.social.demo.base.NavPath

fun NavGraphBuilder.authFlow(
    navController: NavController
) {

    composable(route = NavPath.AUTH) {
        AuthPage(
            navController,
            viewModel = hiltViewModel()
        )
    }

}