package com.alorma.gasstations.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.alorma.gasstations.ui.gasstationslist.GasStationsListScreen
import com.alorma.gasstations.ui.home.HomeScreen
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator

@Composable
fun App() {
  MaterialTheme {
    PreComposeApp {
      val navigator = rememberNavigator()
      NavHost(
        navigator = navigator,
        initialRoute = "/gasStationsList"
      ) {
        scene("/gasStationsList") {
          GasStationsListScreen(
            onNavigate = {
              navigator.navigate("/home")
            },
          )
        }
        scene("/home") {
          HomeScreen()
        }
      }
    }
  }
}