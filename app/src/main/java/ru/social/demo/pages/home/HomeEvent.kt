package ru.social.demo.pages.home

sealed class HomeEvent {
    object LoadData : HomeEvent()
    object UserClicked : HomeEvent()
}