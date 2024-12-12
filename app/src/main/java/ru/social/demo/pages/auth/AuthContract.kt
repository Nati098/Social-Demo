package ru.social.demo.pages.auth

import ru.social.demo.base.BaseEvent
import ru.social.demo.base.BaseViewState

class AuthContract {

    sealed interface Event : BaseEvent {
        data class SignInClicked(val onSuccess: () -> Unit) : Event
        object SignInHostClicked : Event
        object SignUpToggle : Event
        data class FinishSignUpClicked(val onSuccess: () -> Unit) : Event
    }

    data class State(
        val email: String = "",
        val password: String = "",
        val name: String = "",
        val imageUrl: String = "",
        val isSignUp: Boolean = false,
        val error: String = ""
    ) : BaseViewState {

        fun isValid() = email.isNotBlank() && password.isNotBlank()

        fun isValidUserData() = isValid() && name.isNotBlank()

    }

}