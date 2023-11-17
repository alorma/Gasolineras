package com.alorma.gasstations.di

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.core.context.startKoin

fun initKoin() {
  startKoin {
    modules(appModules)
    modules(PlatformModule())
  }
}

fun debugBuild() {
  Napier.base(DebugAntilog(defaultTag = "Alorma"))
}