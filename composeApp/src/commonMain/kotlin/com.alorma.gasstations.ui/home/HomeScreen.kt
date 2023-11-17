package com.alorma.gasstations.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun HomeScreen(
  onNavigateToGasStations: () -> Unit,
  onNavigateToProducts: () -> Unit,
) {
  Scaffold {
    Column {
      Button(onClick = onNavigateToProducts) {
        Text("Products")
      }
      Button(onClick = onNavigateToGasStations) {
        Text("Terrassa gas stations")
      }
    }
  }
}