package com.alorma.gasstations.ui.gasstationslist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.alorma.gasstations.domain.GasStationsInfo
import com.alorma.gasstations.ui.UiState
import org.koin.compose.koinInject

@Composable
fun GasStationsListScreen(
  viewModel: GasStationsListViewModel = koinInject()
) {

  val state by viewModel.state.collectAsState()

  LaunchedEffect(Unit) {
    viewModel.onInit()
  }

  when (val currentState = state) {
    UiState.Initial -> {}
    is UiState.Success -> {
      GasStationsListContent(currentState.state)
    }

    is UiState.Error -> {}
  }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GasStationsListContent(state: GasStationsInfo) {
  LazyColumn {
    stickyHeader {
      Text(text = "Fresh data: ${state.freshData}")
    }
    items(state.gasStations) { station ->
      Text(text = station.name)
    }
  }
}
