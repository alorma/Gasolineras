package com.alorma.gasstations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.alorma.gasstations.di.PlatformModule
import com.alorma.gasstations.di.appModules
import com.alorma.gasstations.ui.App
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    startKoin {
      androidContext(applicationContext)
      modules(appModules)
      modules(PlatformModule())
    }

    setContent {
      App()
    }
  }
}