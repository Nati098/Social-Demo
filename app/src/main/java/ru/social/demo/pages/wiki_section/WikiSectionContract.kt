package ru.social.demo.pages.wiki_section

import ru.social.demo.base.BaseEvent
import ru.social.demo.base.BaseViewState
import ru.social.demo.data.model.rpg.RpgClass
import ru.social.demo.data.model.rpg.RpgRace
import ru.social.demo.data.model.rpg.ShortInfo

class WikiSectionContract {

    sealed interface Event : BaseEvent {
        data class LoadData<T>(val type: T): Event
        data class Reload<T>(val type: T): Event
        data class TabChanged(val idx: Int): Event
    }

    data class State(
        val selectedTab: Int,
        val classes: List<RpgClass>?,
        val races: List<RpgRace>?,
        val monsters: List<ShortInfo>?,
        val isClassesLoading: Boolean,
        val isRacesLoading: Boolean,
        val isMonstersLoading: Boolean,
        val isError: Boolean
    ): BaseViewState {

        fun isEmptyState() =
            classes.isNullOrEmpty() && races.isNullOrEmpty() && monsters.isNullOrEmpty()

        fun copyObj(
            selectedTab: Int = this.selectedTab,
            classes: List<RpgClass>? = this.classes,
            races: List<RpgRace>? = this.races,
            monsters: List<ShortInfo>? = this.monsters,
            isClassesLoading: Boolean = this.isClassesLoading,
            isRacesLoading: Boolean = this.isRacesLoading,
            isMonstersLoading: Boolean = this.isMonstersLoading,
            isError: Boolean = this.isError,
        ): State =
            State(
                selectedTab,
                classes,
                races,
                monsters,
                isClassesLoading,
                isRacesLoading,
                isMonstersLoading,
                isError
            )

    }

}