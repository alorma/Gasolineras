package com.alorma.gasstations.ui.gasstationslist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.alorma.gasstations.domain.GasStationsInfo
import com.alorma.gasstations.ui.UiState
import org.koin.compose.koinInject

@Composable
fun GasStationsListScreen(
  viewModel: GasStationsListViewModel = koinInject(),
  onNavigate: () -> Unit,
) {

  val state by viewModel.state.collectAsState()

  LaunchedEffect(Unit) {
    viewModel.onInit()
  }

  when (val currentState = state) {
    UiState.Initial -> {}
    is UiState.Success -> {
      GasStationsListContent(
        state = currentState.state,
        onNavigate = onNavigate,
      )
    }

    is UiState.Error -> {}
  }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GasStationsListContent(
  state: GasStationsInfo,
  onNavigate: () -> Unit,
) {
  LazyColumn {
    stickyHeader {
      Column {
        Text(text = "Fresh data: ${state.freshData}")
        Button(onClick = onNavigate) {
          Text("Click me")
        }
      }
    }
    items(state.gasStations) { station ->
      Text(text = station.name)
    }
  }
}
