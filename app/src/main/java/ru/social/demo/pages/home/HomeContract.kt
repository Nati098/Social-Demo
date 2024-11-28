package ru.social.demo.pages.home

import ru.social.demo.base.BaseEvent
import ru.social.demo.base.BaseViewState
import ru.social.demo.data.model.Post
import ru.social.demo.data.model.User

class HomeContract {

    sealed class Event : BaseEvent {
        object LoadData : Event()
        object LoadUser : Event()
        object ReloadData : Event()
        object UserClicked : Event()
    }

    sealed class State : BaseViewState {
        data class SuccessData(val data: List<Post>) : Success, State()
        data class SuccessUser(val data: User?) : Success, State()
        object LoadingData : State()
        object LoadingUser : State()
        object Error : State()

        interface Success
    }

}