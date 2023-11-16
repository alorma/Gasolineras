package com.alorma.gasstations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import com.alorma.gasstations.cache.DatabaseDriverFactory
import com.alorma.gasstations.domain.GasStationsSdk
import com.alorma.gasstations.ui.App
import com.russhwolf.settings.Settings

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      val databaseDriverFactory = DatabaseDriverFactory(LocalContext.current)
      val settings = Settings()
      val sdk = GasStationsSdk(databaseDriverFactory, settings)
      App(sdk)
    }
  }
}