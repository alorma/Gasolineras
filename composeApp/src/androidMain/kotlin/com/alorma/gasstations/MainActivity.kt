package com.alorma.gasstations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.alorma.gasstations.di.PlatformModule
import com.alorma.gasstations.di.appModules
import com.alorma.gasstations.ui.App
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.logger.Logger
import org.koin.core.logger.MESSAGE

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    Napier.base(DebugAntilog(defaultTag = "Alorma"))

    startKoin {
      androidContext(applicationContext)
      modules(appModules)
      modules(PlatformModule())
      logger(object : Logger() {
        override fun display(level: Level, msg: MESSAGE) {
          when (level) {
            Level.DEBUG -> Napier.d { msg }
            Level.INFO -> Napier.i { msg }
            Level.WARNING -> Napier.w { msg }
            Level.ERROR -> Napier.e { msg }
            Level.NONE -> Napier.wtf { msg }
          }
        }
      })
    }

    setContent {
      App()
    }
  }
}