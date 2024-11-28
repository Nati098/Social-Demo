package ru.social.demo.pages.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.social.demo.base.EventHandler
import ru.social.demo.data.model.Post
import ru.social.demo.data.model.User
import ru.social.demo.services.FirestoreInteractor
import ru.social.demo.services.FsPath
import ru.social.demo.utils.NetworkUtils
import javax.inject.Inject

private const val USER_ID = "muggRsDlSSfuP5Zv1mlHXHxHlZg1"

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel(), EventHandler<HomeContract.Event> {

    private val _postsViewState: MutableLiveData<HomeContract.State> = MutableLiveData(HomeContract.State.LoadingData)
    val postsViewState: LiveData<HomeContract.State> = _postsViewState

    private val _userViewState: MutableLiveData<HomeContract.State> = MutableLiveData(HomeContract.State.LoadingUser)
    val userViewState: LiveData<HomeContract.State> = _userViewState

    init {
        getAll()
    }

    override fun handle(event: HomeContract.Event) {
        when(val currentState = _postsViewState.value) {
            is HomeContract.State.SuccessData -> handleSuccess(event, currentState)
            is HomeContract.State.SuccessUser -> {}
            is HomeContract.State.LoadingData -> handleLoading(event)
            is HomeContract.State.LoadingUser -> {}
            is HomeContract.State.Error -> handleError(event)
            null -> handleError(event)
        }

        when(val currentState = _userViewState.value) {
            is HomeContract.State.SuccessData -> {}
            is HomeContract.State.SuccessUser -> handleSuccess(event, currentState)
            is HomeContract.State.LoadingData -> {}
            is HomeContract.State.LoadingUser -> {}
            is HomeContract.State.Error -> handleError(event)
            null -> handleError(event)
        }
    }

    private fun getAll() {
        viewModelScope.launch {
            fetchUser()
            fetchPosts()
        }
    }

    private fun handleSuccess(event: HomeContract.Event, state: HomeContract.State.Success) {
        when(event) {
            HomeContract.Event.LoadData -> {}
            HomeContract.Event.LoadUser -> {}
            HomeContract.Event.UserClicked -> onUserClick()
            else -> throw NotImplementedError("Invalid event ($event) for state $state")
        }
    }

    private fun handleLoading(event: HomeContract.Event) {
        when(event) {
            HomeContract.Event.LoadData -> viewModelScope.launch { fetchPosts() }
            HomeContract.Event.LoadUser -> viewModelScope.launch { fetchUser() }
            else -> throw NotImplementedError("Invalid event ($event) for ViewState.Loading")
        }
    }

    private fun handleError(event: HomeContract.Event) {
        when(event) {
            HomeContract.Event.LoadData -> viewModelScope.launch { fetchPosts(isRefresh = true) }
            HomeContract.Event.LoadUser -> viewModelScope.launch { fetchUser() }
            else -> throw NotImplementedError("Invalid event ($event) for ViewState.Loading")
        }
    }

    private suspend fun fetchUser() {
        NetworkUtils.makeCall {
            FirestoreInteractor.getInstance().readData<User>(
                path = FsPath.USERS,
                docId = USER_ID,
                onSuccess = { result ->
                    _userViewState.postValue(HomeContract.State.SuccessUser(data = result))
                },
                onError = {
                    _userViewState.postValue(HomeContract.State.Error)
                }
            )
        }

    }

    private suspend fun fetchPosts(isRefresh: Boolean = false) {
        if (isRefresh) {
            _postsViewState.postValue(HomeContract.State.LoadingData)
        }

        NetworkUtils.makeCall {
            FirestoreInteractor.getInstance().readData<Post>(
                path = FsPath.POSTS,
                onSuccess = { result ->
                    val data = result?.sortedByDescending { it.createDate } ?: emptyList()
                    _postsViewState.postValue(HomeContract.State.SuccessData(data = data))
                },
                onError = {
                    _postsViewState.postValue(HomeContract.State.Error)
                }
            )
        }

    }

    private fun onUserClick() {
        // TODO: to profile of author of post
    }

}
