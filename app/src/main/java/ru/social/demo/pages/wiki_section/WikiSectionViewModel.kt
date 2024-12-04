package ru.social.demo.pages.wiki_section

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.social.demo.base.EventHandler
import ru.social.demo.data.model.rpg.RpgClass
import ru.social.demo.data.model.rpg.RpgRace
import ru.social.demo.data.model.rpg.RpgTab
import ru.social.demo.services.retrofit.RpgApiClient
import javax.inject.Inject

@HiltViewModel
class WikiSectionViewModel @Inject constructor() : ViewModel(), EventHandler<WikiSectionContract.Event> {

    private val _compendiumState: MutableLiveData<WikiSectionContract.State> =
        MutableLiveData(WikiSectionContract.State(
            selectedTab = 0,
            classes = null,
            races = null,
            monsters = null,
            isClassesLoading = true,
            isRacesLoading = true,
            isMonstersLoading = true,
            isError = false
        ))
    val compendiumState: LiveData<WikiSectionContract.State> = _compendiumState

    override fun handle(event: WikiSectionContract.Event) {
        when(event) {
            is WikiSectionContract.Event.LoadData<*> -> handleType(event)
            is WikiSectionContract.Event.Reload<*> -> handleType(event)
            is WikiSectionContract.Event.TabChanged -> handleTabChanged(event.idx)
        }
    }


    private fun handleType(event: WikiSectionContract.Event.LoadData<*>) {
        when(event.type) {
            RpgTab.CLASS -> viewModelScope.launch { fetchClasses() }
            RpgTab.RACE -> viewModelScope.launch { fetchRaces() }
            RpgTab.MONSTER -> viewModelScope.launch { fetchMonsters() }
        }
    }

    private fun handleType(event: WikiSectionContract.Event.Reload<*>) {
        when(event.type) {
            RpgTab.CLASS -> viewModelScope.launch { fetchClasses(true) }
            RpgTab.RACE -> viewModelScope.launch { fetchRaces(true) }
            RpgTab.MONSTER -> viewModelScope.launch { fetchMonsters(true) }
        }
    }

    private fun handleTabChanged(idx: Int?) {
        _compendiumState.postValue(_compendiumState.value?.copyObj(selectedTab = idx ?: 0))
    }

    private suspend fun fetchClasses(isRefresh: Boolean = false) {
        if (isRefresh) {
            _compendiumState.postValue(_compendiumState.value?.copyObj(isClassesLoading = true))
        }

        CoroutineScope(Dispatchers.IO).launch {
            val result = mutableListOf<RpgClass>()

            RpgTab.CLASS.ids().forEach { id ->
                val res = RpgApiClient.service.getClassById(id)
                result.add(res)
                Log.d("TEST", "CLASS for $id : ${res.name}")
            }

            _compendiumState.postValue(
                _compendiumState.value?.copyObj(classes = result, isClassesLoading = false)
            )
        }

    }

    private suspend fun fetchRaces(isRefresh: Boolean = false) {
        if (isRefresh) {
            _compendiumState.postValue(_compendiumState.value?.copyObj(isRacesLoading = true))
        }

        CoroutineScope(Dispatchers.IO).launch {
            val result = mutableListOf<RpgRace>()

            RpgTab.RACE.ids().forEach { id ->
                val res = RpgApiClient.service.getRaceById(id)
                result.add(res)
                Log.d("TEST", "RACE for $id : ${res.name}")
            }

            _compendiumState.postValue(
                _compendiumState.value?.copyObj(races = result, isRacesLoading = false)
            )
        }
    }

    private suspend fun fetchMonsters(isRefresh: Boolean = false) {
        if (isRefresh) {
            _compendiumState.postValue(_compendiumState.value?.copyObj(isMonstersLoading = true))
        }

        CoroutineScope(Dispatchers.IO).launch {
            val result = RpgApiClient.service.getMonsters()
            Log.d("TEST", "MONSTERS count = ${result.count}")
            _compendiumState.postValue(
                _compendiumState.value?.copyObj(monsters = result.results, isMonstersLoading = false)
            )
        }
    }

}