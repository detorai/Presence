package org.example.project.ui.login

import org.example.presenceapp.ui.base.ViewEvent
import org.example.presenceapp.ui.base.ViewSideEffect
import org.example.presenceapp.ui.base.ViewState


class LoginContract {

    sealed class Event: ViewEvent {
        data class LoginChanged(val login: String) : Event()
        data class PasswordChanged(val password: String) : Event()
        data object ToggleCheck : Event()
        data object SubmitLogin : Event()
        data object DismissError : Event()
    }

    data class State(
        var login: String = "",
        var password: String = "",
        var error: String? = null,
        var success: Boolean = false,
        var check: Boolean = false,
    ): ViewState

    sealed class Effect: ViewSideEffect {
        sealed class Navigation : Effect() {
            data object ToHome : Navigation()
        }
        data class ShowError(val message: String?) : Effect()
    }
}
