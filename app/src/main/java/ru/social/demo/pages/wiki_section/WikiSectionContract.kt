package ru.social.demo.pages.wiki_section

import ru.social.demo.base.BaseEvent
import ru.social.demo.base.BaseViewState
import ru.social.demo.data.model.Post

class WikiSectionContract {

    sealed interface Event : BaseEvent {
        object LoadData : Event
        object Reload : Event
    }

    sealed interface State : BaseViewState {
        data class SuccessData(val data: List<Post>) : State
        object LoadingData : State
        object Error : State
    }

}