package com.alorma.gasstations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import com.alorma.gasstations.cache.DatabaseDriverFactory
import com.alorma.gasstations.di.appModules
import com.alorma.gasstations.domain.GasStationsSdk
import com.alorma.gasstations.ui.App
import com.russhwolf.settings.Settings
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    startKoin {
      androidContext(applicationContext)
      modules(appModules)
    }

    setContent {
      val databaseDriverFactory = DatabaseDriverFactory(LocalContext.current)
      val settings = Settings()
      val sdk = GasStationsSdk(databaseDriverFactory, settings)
      App(sdk)
    }
  }
}