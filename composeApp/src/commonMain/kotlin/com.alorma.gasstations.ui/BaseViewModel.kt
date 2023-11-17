package com.alorma.gasstations.ui

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

open class BaseViewModel<SUCCESS, ERROR> : ViewModel() {

  private var _state: MutableStateFlow<UiState<ERROR, SUCCESS>> = MutableStateFlow(UiState.Initial)
  val state: StateFlow<UiState<ERROR, SUCCESS>> = _state

  fun launch(block: suspend CoroutineScope.() -> Unit) = viewModelScope.launch { block() }

  protected fun emitSuccess(state: SUCCESS) {
    viewModelScope.launch {
      _state.emit(UiState.Success(state))
    }
  }

  protected fun updateIfSuccess(block: (SUCCESS) -> SUCCESS) {
    _state.update {
      when (it) {
        is UiState.Success<SUCCESS> -> block(it.state).asSuccess()
        else -> it
      }
    }
  }
}