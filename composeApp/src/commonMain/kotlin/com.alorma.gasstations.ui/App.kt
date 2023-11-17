package com.alorma.gasstations.ui

import androidx.compose.runtime.Composable
import com.alorma.gasstations.ui.gasstationslist.GasStationsListScreen
import com.alorma.gasstations.ui.home.HomeScreen
import com.alorma.gasstations.ui.theme.AppTheme
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator

@Composable
fun App() {
  AppTheme {
    PreComposeApp {
      val navigator = rememberNavigator()
      NavHost(
        navigator = navigator,
        initialRoute = "/home"
      ) {
        scene("/home") {
          HomeScreen(
            onNavigate = {
              navigator.navigate("/gasStationsList")
            },
          )
        }
        scene("/gasStationsList") {
          GasStationsListScreen(
            onBack = { navigator.goBack() },
          )
        }
      }
    }
  }
}