package ru.social.demo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.social.demo.base.EventHandler
import ru.social.demo.data.model.User
import ru.social.demo.services.FirestoreClient
import ru.social.demo.services.FsPath
import ru.social.demo.utils.NetworkUtils
import javax.inject.Inject

private const val USER_ID = "muggRsDlSSfuP5Zv1mlHXHxHlZg1"

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel(), EventHandler<MainContract.Event> {

    private val _userViewState: MutableLiveData<MainContract.State> = MutableLiveData(MainContract.State.LoadingUser)
    val userViewState: LiveData<MainContract.State> = _userViewState

    override fun handle(event: MainContract.Event) {
        when(event) {
            is MainContract.Event.LoadUser -> handleUserState(event)
            is MainContract.Event.Reload -> viewModelScope.launch { fetchUser() }
            is MainContract.Event.UserClicked -> {}
        }
    }

    private fun handleUserState(event: MainContract.Event) {
        when(val currentState = _userViewState.value) {
            is MainContract.State.SuccessUser -> {
                when(event) {
                    is MainContract.Event.LoadUser -> viewModelScope.launch { fetchUser() }
                    is MainContract.Event.UserClicked -> {}
                    else -> throw NotImplementedError("Invalid event ($event) for state $currentState")
                }
            }
            is MainContract.State.LoadingUser -> viewModelScope.launch { fetchUser() }
            is MainContract.State.Error -> viewModelScope.launch { fetchUser() }
            else -> throw NotImplementedError("Invalid event ($event) for $currentState")
        }
    }

    private suspend fun fetchUser() {
        NetworkUtils.makeCall {
            FirestoreClient.getInstance().readData<User>(
                path = FsPath.USERS,
                docId = USER_ID,
                onSuccess = { result ->
                    _userViewState.postValue(MainContract.State.SuccessUser(data = result))
                },
                onError = {
                    _userViewState.postValue(MainContract.State.Error)
                }
            )
        }
    }

}