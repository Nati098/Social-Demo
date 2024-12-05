package ru.social.demo.pages.home

import ru.social.demo.base.BaseEvent
import ru.social.demo.base.BaseViewState
import ru.social.demo.data.model.Post

class HomeContract {

    sealed interface Event : BaseEvent {
        object LoadData : Event
        object ReloadData : Event
        object UserClicked : Event
    }

    sealed interface State : BaseViewState {
        data class SuccessData(val data: List<Post>) : State
        object LoadingData : State
        object Error : State
    }

}