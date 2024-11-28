package ru.social.demo.base

import ru.social.demo.R


object NavPath {

    val library = NavBarPath.LIBRARY.route
    val wiki = NavBarPath.WIKI.route
    val main = NavBarPath.MAIN.route
    val events = NavBarPath.EVENTS.route

    val profile = "profile"

}

enum class NavBarPath(val label: Int, val idActive: Int, val idInactive: Int, val route: String) {
    LIBRARY(R.string.library, R.drawable.wiki_filled, R.drawable.wiki, "library"),
    WIKI(R.string.wiki, R.drawable.wiki_filled, R.drawable.wiki, "wiki"),
    MAIN(R.string.main, R.drawable.main_filled, R.drawable.main, "home"),
    EVENTS(R.string.events, R.drawable.events_filled, R.drawable.events, "events"),
//    PROFILE(R.string.profile, R.drawable.profile_filled, R.drawable.profile)
}