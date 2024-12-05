package ru.social.demo

import ru.social.demo.base.BaseEvent
import ru.social.demo.base.BaseViewState
import ru.social.demo.data.model.User

class MainContract {

    sealed interface Event : BaseEvent {
        object LoadUser : Event
        object Reload : Event
        object UserClicked : Event
    }

    sealed interface State : BaseViewState {
        data class SuccessUser(val data: User?) : State
        object LoadingUser : State
        object Error : State

    }

}