package com.alorma.gasstations.ui.gasstationslist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.alorma.gasstations.domain.GasStationsInfo
import com.alorma.gasstations.ui.UiState
import moe.tlaster.precompose.koin.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GasStationsListScreen(
  viewModel: GasStationsListViewModel = koinViewModel(
    vmClass = GasStationsListViewModel::class,
  ),
  onBack: () -> Unit,
) {
  val state by viewModel.state.collectAsState()

  val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
  Scaffold(
    modifier = Modifier.nestedScroll(
      connection = scrollBehavior.nestedScrollConnection
    ),
    topBar = {
      val currentState = state
      if (currentState is UiState.Success) {
        TopAppBar(
          title = {
            Column {
              Text(text = "Gas stations")
              Text(
                text = "Fresh data: ${currentState.state.freshData}",
                style = MaterialTheme.typography.labelSmall,
              )
            }
          },
          scrollBehavior = scrollBehavior,
          navigationIcon = {
            IconButton(onClick = onBack) {
              Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
          },
        )
      }
    },
  ) { paddingValues ->
    LaunchedEffect(Unit) {
      viewModel.onInit()
    }

    Column(
      modifier = Modifier.padding(
        top = paddingValues.calculateTopPadding(),
      ),
    ) {
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
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GasStationsListContent(
  state: GasStationsInfo,
) {
  LazyColumn(
    verticalArrangement = Arrangement.spacedBy(8.dp),
  ) {
    items(state.gasStations) { station ->
      Card(
        modifier = Modifier
          .padding(horizontal = 8.dp)
          .fillMaxWidth(),
      ) {
        Column(
          modifier = Modifier.padding(all = 24.dp),
          verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
          Text(
            text = station.name.trim(),
            style = MaterialTheme.typography.titleMedium,
          )
          Text(
            text = station.address.trim(),
            style = MaterialTheme.typography.bodyLarge,
          )
        }
      }
    }
  }
}
