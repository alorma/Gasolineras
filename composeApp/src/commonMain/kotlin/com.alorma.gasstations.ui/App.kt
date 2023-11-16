package com.alorma.gasstations.ui

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
import com.alorma.gasstations.domain.GasStation
import com.alorma.gasstations.domain.GasStationsSdk

@Composable
fun App(
  sdk: GasStationsSdk
) {
  MaterialTheme {
    var stations by remember {
      mutableStateOf<List<GasStation>>(emptyList())
    }
    LaunchedEffect(Unit) {
      stations = sdk.getGasStations(false)
    }

    LazyColumn {
      items(stations) { station ->
        Text(text = station.name)
      }
    }
  }
}