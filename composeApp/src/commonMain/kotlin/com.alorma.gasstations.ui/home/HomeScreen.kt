package com.alorma.gasstations.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun HomeScreen(
  onNavigate: () -> Unit,
) {
  Scaffold {
    Column {
      Text(text = "Home")
      Button(onClick = onNavigate) {
        Text("Click me")
      }
    }
  }
}