package com.alorma.gasstations.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.alorma.gasstations.domain.GasStationsInfo
import com.alorma.gasstations.domain.GasStationsSdk

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun App(
  sdk: GasStationsSdk
) {
  MaterialTheme {
    var gasStationsInfo by remember {
      mutableStateOf(
        GasStationsInfo(
          gasStations = emptyList(),
          freshData = false
        )
      )
    }
    LaunchedEffect(Unit) {
      gasStationsInfo = sdk.getGasStations()
    }

    LazyColumn {
      stickyHeader {
        Text(text = "Fresh data: ${gasStationsInfo.freshData}")
      }
      items(gasStationsInfo.gasStations) { station ->
        Text(text = station.name)
      }
    }
  }
}