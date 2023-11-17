package com.alorma.gasstations.ui.gasstationslist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.alorma.gasstations.domain.GasStationsInfo
import com.alorma.gasstations.ui.UiState
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GasStationsListScreen(
  viewModel: GasStationsListViewModel = koinInject(),
  onBack: () -> Unit,
) {

  val state by viewModel.state.collectAsState()
  Scaffold(
    topBar = {
      if (state is UiState.Success) {
        TopAppBar(
          title = { Text(text = "Gas stations") },
          navigationIcon = {
            IconButton(onClick = onBack) {
              Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
          },
        )
      }
    },
  ) {

    LaunchedEffect(Unit) {
      viewModel.onInit()
    }

    when (val currentState = state) {
      UiState.Initial -> {}
      is UiState.Success -> {
        GasStationsListContent(
          state = currentState.state,
        )
      }

      is UiState.Error -> {}
    }
  }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GasStationsListContent(
  state: GasStationsInfo,
) {
  LazyColumn {
    stickyHeader {
      Column {
        Text(text = "Fresh data: ${state.freshData}")
      }
    }
    items(state.gasStations) { station ->
      Text(text = station.name)
    }
  }
}
