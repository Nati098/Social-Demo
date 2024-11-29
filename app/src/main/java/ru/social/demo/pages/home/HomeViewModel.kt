package ru.social.demo.pages.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.social.demo.base.EventHandler
import ru.social.demo.data.model.Post
import ru.social.demo.services.FirestoreInteractor
import ru.social.demo.services.FsPath
import ru.social.demo.utils.NetworkUtils
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel(), EventHandler<HomeContract.Event> {

    private val _postsViewState: MutableLiveData<HomeContract.State> = MutableLiveData(HomeContract.State.LoadingData)
    val postsViewState: LiveData<HomeContract.State> = _postsViewState

    override fun handle(event: HomeContract.Event) {
        when(event) {
            is HomeContract.Event.LoadData -> handlePostsState(event)
            is HomeContract.Event.ReloadData -> viewModelScope.launch { fetchPosts(true) }
            is HomeContract.Event.UserClicked -> onUserClick()
        }
    }

    private fun handlePostsState(event: HomeContract.Event) {
        when(_postsViewState.value) {
            is HomeContract.State.SuccessData -> {}
            is HomeContract.State.LoadingData -> viewModelScope.launch { fetchPosts() }
            is HomeContract.State.Error -> handleError(event)
            else -> handleError(event)
        }
    }

    private fun handleError(event: HomeContract.Event) {
        when(event) {
            HomeContract.Event.LoadData -> viewModelScope.launch { fetchPosts(isRefresh = true) }
            else -> throw NotImplementedError("Invalid event ($event) for ViewState.Loading")
        }
    }

    private suspend fun fetchPosts(isRefresh: Boolean = false) {
        Log.d("TEST", "HomeVM $this state = ${_postsViewState.value}")
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
