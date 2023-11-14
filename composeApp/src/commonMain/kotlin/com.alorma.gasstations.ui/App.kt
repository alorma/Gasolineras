package com.alorma.gasstations.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import com.alorma.gasstations.domain.GasStation
import com.alorma.gasstations.domain.GasStationsSdk

@Composable
fun App(
  sdk: GasStationsSdk
) {
  MaterialTheme {
    val stations = remember { mutableListOf<GasStation>() }
    LaunchedEffect(Unit) {
      stations.clear()
      val values = sdk.getGasStations(true)
      stations.addAll(values)
    }

    LazyColumn {
      items(stations) { station ->
        Text(text = station.name)
      }
    }
  }
}