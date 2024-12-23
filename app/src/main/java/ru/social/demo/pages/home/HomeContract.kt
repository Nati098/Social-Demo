package ru.social.demo.pages.home

import ru.social.demo.base.BaseEvent
import ru.social.demo.base.BaseViewState
import ru.social.demo.data.model.Post

class HomeContract {

    sealed interface Event : BaseEvent {
        object LoadFeed : Event
        object Reload : Event
        data class EditPostClicked(val post: Post?) : Event
        object UserClicked : Event
    }

    sealed interface State : BaseViewState {
        data class SuccessFeed(val data: List<Post>) : State
        object LoadingFeed : State

        data class PostToEdit(val data: Post?) : State

        object Error : State
    }

}