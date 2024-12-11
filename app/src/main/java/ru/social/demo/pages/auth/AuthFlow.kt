package ru.social.demo.pages.auth

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.social.demo.base.NavPath

fun NavGraphBuilder.authFlow() {

    composable(route = NavPath.AUTH) {
        AuthPage(
            viewModel = hiltViewModel()
        )
    }

}