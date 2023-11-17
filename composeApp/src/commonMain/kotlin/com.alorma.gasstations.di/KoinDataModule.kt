package com.alorma.gasstations.di

import com.alorma.gasstations.domain.GasStationsSdk
import com.russhwolf.settings.Settings
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

object KoinDataModule {
  operator fun invoke() = module {
    factory { Settings() }
    factoryOf(::GasStationsSdk)
  }
}