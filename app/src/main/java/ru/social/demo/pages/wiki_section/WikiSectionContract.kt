package ru.social.demo.pages.wiki_section

import ru.social.demo.base.BaseEvent
import ru.social.demo.base.BaseViewState
import ru.social.demo.data.model.rpg.RpgClass
import ru.social.demo.data.model.rpg.RpgRace
import ru.social.demo.data.model.rpg.RpgTab
import ru.social.demo.data.model.rpg.ShortInfo

class WikiSectionContract {

    sealed interface Event : BaseEvent {
        data class LoadData<T>(val type: T): Event
        data class Reload<T>(val type: T): Event
        data class TabChanged(val idx: Int): Event
        data class ItemClicked<T>(val type: T, val idx: String): Event
    }

    data class State(
        val selectedTab: Int,
        val bottomSheetItem: Any?,
        val classes: List<RpgClass>?,
        val races: List<RpgRace>?,
        val monsters: List<ShortInfo>?,
        val isClassesLoading: Boolean,
        val isRacesLoading: Boolean,
        val isMonstersLoading: Boolean,
        val isMonsterLoading: Boolean,
        val isError: Boolean
    ): BaseViewState {

        fun isEmptyState() =
            classes.isNullOrEmpty() && races.isNullOrEmpty() && monsters.isNullOrEmpty()

        fun isDataEmpty(type: RpgTab) = when(type) {
            RpgTab.CLASS -> classes.isNullOrEmpty()
            RpgTab.RACE -> races.isNullOrEmpty()
            RpgTab.MONSTER -> monsters.isNullOrEmpty()
            else -> false
        }

    }

}