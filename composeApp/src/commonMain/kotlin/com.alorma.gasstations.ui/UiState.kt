package com.alorma.gasstations.ui

sealed class UiState<out ERROR, out STATE> {
  data object Initial : UiState<Nothing, Nothing>()
  data class Success<out STATE>(val state: STATE) : UiState<Nothing, STATE>()
  data class Error<out ERROR>(val error: ERROR) : UiState<ERROR, Nothing>()
}

fun <T> T.asSuccess(): UiState.Success<T> = UiState.Success(this)
