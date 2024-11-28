package ru.social.demo.base

import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

class AppState(
    val navController: NavHostController
) {

    val bottomBarTabs = NavBarPath.entries.toTypedArray()
    private val bottomBarPaths = bottomBarTabs.map { it.route }

    val isBottomBarVisible: Boolean
        @Composable get() = navController
            .currentBackStackEntryAsState()
            .value?.destination?.route in bottomBarPaths

    val currentRoute: String?
        get() = navController.currentDestination?.route

    fun navigateToBottomBarTab(route: String) {
        if (route != currentRoute) {
            navController.navigate(route) {
                launchSingleTop = true
                restoreState = true
                navController.graph
                popUpTo(findStartDestination(navController.graph).id) {
                    saveState = true
                }
            }
        }
    }

    private tailrec fun findStartDestination(graph: NavDestination): NavDestination {
        return if (graph is NavGraph)
            findStartDestination(graph.findNode(graph.startDestinationId)!!)
        else
            graph
    }

}