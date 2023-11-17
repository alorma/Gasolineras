package com.alorma.gasstations.ui

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.alorma.gasstations.products.ui.ProductsScreen
import com.alorma.gasstations.ui.gasstationslist.GasStationsListScreen
import com.alorma.gasstations.ui.home.HomeScreen
import com.alorma.gasstations.ui.theme.AppTheme
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.navigation.transition.NavTransition
import org.koin.compose.KoinContext

@Composable
fun App() {
  KoinContext {
    AppTheme {
      PreComposeApp {
        val navigator = rememberNavigator()
        NavHost(
          navigator = navigator,
          initialRoute = "/home",
          navTransition = remember {
            NavTransition(
              createTransition = slideInHorizontally() + fadeIn(),
              destroyTransition = slideOutHorizontally() + fadeOut(),
            )
          },
        ) {
          scene("/home") {
            HomeScreen(
              onNavigateToGasStations = {
                navigator.navigate("/gasStationsList")
              },
              onNavigateToProducts = {
                navigator.navigate("/products")
              },
            )
          }
          scene("/gasStationsList") {
            GasStationsListScreen(
              onBack = { navigator.goBack() },
            )
          }
          scene(
            route = "/products",
            navTransition = NavTransition(
              createTransition = fadeIn() + slideInVertically(),
              destroyTransition = slideOutVertically(),
            )
          ) {
            ProductsScreen(
              onBack = { navigator.goBack() },
            )
          }
        }
      }
    }
  }
}