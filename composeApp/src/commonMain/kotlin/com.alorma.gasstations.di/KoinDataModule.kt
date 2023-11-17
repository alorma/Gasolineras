package com.alorma.gasstations.di

import com.russhwolf.settings.Settings
import org.koin.dsl.module

object KoinDataModule {
  operator fun invoke() = module {
    factory { Settings() }
  }
}