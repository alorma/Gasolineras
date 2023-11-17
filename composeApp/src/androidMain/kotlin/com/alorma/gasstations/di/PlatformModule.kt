package com.alorma.gasstations.di

import com.alorma.gasstations.cache.DatabaseDriverFactory
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

actual object PlatformModule {
  operator fun invoke() = module {
    factoryOf(::DatabaseDriverFactory)
  }
}