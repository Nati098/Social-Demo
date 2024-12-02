package ru.social.demo.base

import ru.social.demo.R


object NavPath {

//    val library = NavBarPath.LIBRARY.route
//    val wiki = NavBarPath.WIKI.route
//    val home = NavBarPath.HOME.route
//    val events = NavBarPath.EVENTS.route

    const val MAIN = "main"

    const val PROFILE = "profile"
    const val POST_EDITOR = "postEditor"

}

enum class NavBarPath(val label: Int, val idActive: Int, val idInactive: Int, val route: String) {
    LIBRARY(R.string.library, R.drawable.wiki_filled, R.drawable.wiki, "libraryRoot"),
    WIKI(R.string.wiki, R.drawable.wiki_filled, R.drawable.wiki, "wikiRoot"),
    HOME(R.string.home, R.drawable.home_filled, R.drawable.home, "homeRoot"),
    EVENTS(R.string.events, R.drawable.events_filled, R.drawable.events, "eventsRoot")
}