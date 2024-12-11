package ru.social.demo.pages.auth

import ru.social.demo.base.BaseEvent
import ru.social.demo.base.BaseViewState

class AuthContract {

    sealed interface Event : BaseEvent {
        object SignInClicked : Event
        object SignUpToggle : Event
        object FinishSignUpClicked : Event
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