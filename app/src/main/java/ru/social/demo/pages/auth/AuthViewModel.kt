package ru.social.demo.pages.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.social.demo.base.EventHandler
import ru.social.demo.data.SharedPrefs
import ru.social.demo.data.model.User
import ru.social.demo.services.AuthClient
import ru.social.demo.services.FirestoreClient
import ru.social.demo.services.FsPath
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val sharedPrefs: SharedPrefs
): ViewModel(), EventHandler<AuthContract.Event> {

    private val _userState: MutableLiveData<AuthContract.State> = MutableLiveData(AuthContract.State())
    val userState: LiveData<AuthContract.State> = _userState

    override fun handle(event: AuthContract.Event) {
//        Log.d("TEST", "Auth, event = $event, state = ${_userState.value}")
        when(event) {
            AuthContract.Event.SignInClicked -> handleSignIn()
            AuthContract.Event.SignInHostClicked -> handleSignInHost()
            AuthContract.Event.SignUpToggle -> handleSignUp()
            AuthContract.Event.FinishSignUpClicked -> handleFinishSignUp()
        }
    }

    private fun handleSignIn() {
        _userState.postValue(_userState.value?.copy(error = ""))

        _userState.value?.let { state ->
            AuthClient.getInstance().signIn(
                state.email, state.password,
                onSuccess = {
                    if (it?.uid.isNullOrBlank()) {
                        _userState.postValue(_userState.value?.copy(error = "Wrong user id"))
                    } else {
                        sharedPrefs.setUserId(it?.uid)
                    }
                },
                onError = { _userState.postValue(_userState.value?.copy(error = it)) }
            )
        }

    }

    private fun handleSignInHost() {
        Log.d("TEST", "AuthVM SET HOST AS TRUE")
        sharedPrefs.setIsHost(true)
    }

    private fun handleSignUp() {
        val flag = _userState.value?.isSignUp ?: true
        _userState.postValue(_userState.value?.copy(isSignUp = !flag))
    }

    private fun handleFinishSignUp() {
        _userState.postValue(_userState.value?.copy(error = ""))

        _userState.value?.let { state ->
            AuthClient.getInstance().signUp(
                state.email, state.password,
                onSuccess = { user ->
                    if (user == null) {
                        _userState.postValue(_userState.value?.copy(error = "Can't sign up user"))
                        return@signUp
                    }

                    sharedPrefs.setUserId(user.uid)

                    FirestoreClient.getInstance().setData(
                        FsPath.USERS,
                        id = user.uid,
                        data = User(
                            id = user.uid,
                            name = state.name,
                            imageUrl = state.imageUrl
                        ),
                        onError = { _userState.postValue(_userState.value?.copy(error = it)) }
                    )
                },
                onError = { _userState.postValue(_userState.value?.copy(error = it)) }
            )
        }

    }


    fun onEmailChanged(str: String) {
        _userState.postValue(_userState.value?.copy(email = str))
    }

    fun onPasswordChanged(str: String) {
        _userState.postValue(_userState.value?.copy(password = str))
    }

    fun onNameChanged(str: String) {
        _userState.postValue(_userState.value?.copy(name = str))
    }

    fun onImageUrlChanged(str: String) {
        _userState.postValue(_userState.value?.copy(imageUrl = str))
    }

}