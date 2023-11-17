package com.alorma.gasstations.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.alorma.gasstations.ui.gasstationslist.GasStationsListScreen

@Composable
fun App() {
  MaterialTheme {
    GasStationsListScreen()
  }
}