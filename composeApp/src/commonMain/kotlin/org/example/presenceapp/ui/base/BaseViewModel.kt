package org.example.presenceapp.ui.base

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

interface ViewEvent

interface ViewState

interface ViewSideEffect

const val SIDE_EFFECTS_KEY = "side-effects_key"

abstract class BaseViewModel<Event: ViewEvent, UiState: ViewState, Effect: ViewSideEffect> : ScreenModel {

    abstract fun setInitialState(): UiState
    abstract fun handleEvents(event: Event)

    private val initialState: UiState by lazy { setInitialState() }

    private val _viewState = MutableStateFlow(initialState)
    val viewState: StateFlow<UiState> = _viewState

    private val _event = Channel<Event>(Channel.UNLIMITED)
    private val _effect = Channel<Effect>(Channel.UNLIMITED)
    val effect = _effect.receiveAsFlow()

    init {
        subscribeToEvents()
    }

    private fun subscribeToEvents() {
        screenModelScope.launch {
            _event.consumeAsFlow().collect { event ->
                handleEvents(event)
            }
        }
    }

    fun setEvent(event: Event) {
        screenModelScope.launch {
            _event.send(event)
        }
    }

    protected fun setState(reducer: UiState.() -> UiState) {
        _viewState.value = _viewState.value.reducer()
    }

    protected fun setEffect(builder: () -> Effect) {
        screenModelScope.launch {
            _effect.send(builder())
        }
    }
}