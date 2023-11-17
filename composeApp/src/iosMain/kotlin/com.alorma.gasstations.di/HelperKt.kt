package com.alorma.gasstations.di

import org.koin.core.context.startKoin

fun initKoin() {
  startKoin {
    modules(appModules)
    modules(PlatformModule())
  }
}