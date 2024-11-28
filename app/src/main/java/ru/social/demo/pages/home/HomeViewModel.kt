package ru.social.demo.pages.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.social.demo.base.BaseViewState
import ru.social.demo.base.EventHandler
import ru.social.demo.data.model.Post
import ru.social.demo.services.FirestoreInteractor
import ru.social.demo.services.FsPath

class HomeViewModel : ViewModel(), EventHandler<HomeEvent> {

    private val _homeViewState: MutableLiveData<BaseViewState<Post>> = MutableLiveData(BaseViewState.Loading)
    val homeViewState: LiveData<BaseViewState<Post>> = _homeViewState

    override fun handle(event: HomeEvent) {
        when(val currentState = _homeViewState.value) {
            is BaseViewState.Success -> handleSuccess(event, currentState)
            is BaseViewState.Loading -> handleLoading(event)
            is BaseViewState.Error -> handleError(event)
            null -> handleError(event)
        }
    }

    private fun handleSuccess(event: HomeEvent, state: BaseViewState.Success<Post>) {
        when(event) {
            HomeEvent.LoadData -> {}
            HomeEvent.UserClicked -> onUserClick()
            else -> throw NotImplementedError("Invalid event ($event) for state $state")
        }
    }

    private fun handleLoading(event: HomeEvent) {
        when(event) {
            HomeEvent.LoadData -> fetchPosts()
            else -> throw NotImplementedError("Invalid event ($event) for ViewState.Loading")
        }
    }

    private fun handleError(event: HomeEvent) {
        when(event) {
            HomeEvent.LoadData -> fetchPosts(isRefresh = true)
            else -> throw NotImplementedError("Invalid event ($event) for ViewState.Loading")
        }
    }


    private fun fetchPosts(isRefresh: Boolean = false) {
        if (isRefresh) {
            _homeViewState.postValue(BaseViewState.Loading)
        }

        viewModelScope.launch {
            FirestoreInteractor.getInstance().readData<Post>(
                path = FsPath.POSTS,
                onSuccess = { result ->
                    val data = result?.sortedByDescending { it.createDate } ?: emptyList()
                    _homeViewState.postValue(BaseViewState.Success(data = data))
                },
                onError = {
                    _homeViewState.postValue(BaseViewState.Error)
                }
            )
        }

    }

    private fun onUserClick() {
        // TODO: to profile
    }

}
