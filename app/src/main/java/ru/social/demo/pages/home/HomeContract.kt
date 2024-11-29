package ru.social.demo.pages.home

import ru.social.demo.base.BaseEvent
import ru.social.demo.base.BaseViewState
import ru.social.demo.data.model.Post

class HomeContract {

    sealed class Event : BaseEvent {
        object LoadData : Event()
        object ReloadData : Event()
        object UserClicked : Event()
    }

    sealed class State : BaseViewState {
        data class SuccessData(val data: List<Post>) : Success, State()
        object LoadingData : State()
        object Error : State()

        interface Success
    }

}