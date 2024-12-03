package ru.social.demo.pages.wiki_section

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.social.demo.base.EventHandler
import javax.inject.Inject

@HiltViewModel
class WikiSectionViewModel @Inject constructor() : ViewModel(), EventHandler<WikiSectionContract.Event> {

    private val _dataViewState: MutableLiveData<WikiSectionContract.State> = MutableLiveData(WikiSectionContract.State.LoadingData)
    val dataViewState: LiveData<WikiSectionContract.State> = _dataViewState

    override fun handle(event: WikiSectionContract.Event) {
        when(event) {
            is WikiSectionContract.Event.LoadData -> handleState(event)
            is WikiSectionContract.Event.Reload -> viewModelScope.launch { fetchData(true) }
        }
    }


    private fun handleState(event: WikiSectionContract.Event) {
        when(_dataViewState.value) {
            is WikiSectionContract.State.SuccessData -> {}
            is WikiSectionContract.State.LoadingData -> viewModelScope.launch { fetchData() }
            is WikiSectionContract.State.Error -> handleError(event)
            else -> handleError(event)
        }
    }

    private fun handleError(event: WikiSectionContract.Event) {
        when(event) {
            WikiSectionContract.Event.LoadData -> viewModelScope.launch { fetchData(isRefresh = true) }
            else -> throw NotImplementedError("Invalid event ($event) for ViewState.Loading")
        }
    }

    private suspend fun fetchData(isRefresh: Boolean = false) {

    }

}