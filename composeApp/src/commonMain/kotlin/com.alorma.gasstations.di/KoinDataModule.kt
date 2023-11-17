package com.alorma.gasstations.di

import com.alorma.gasstations.cache.Database
import com.alorma.gasstations.domain.GasStationsSdk
import com.alorma.gasstations.network.GasStationsApi
import com.russhwolf.settings.Settings
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

object KoinDataModule {
  operator fun invoke() = module {
    factoryOf(::Settings)
    factoryOf(::Database)
    factoryOf(::GasStationsApi)
    factoryOf(::GasStationsSdk)
  }
}