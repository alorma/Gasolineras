package com.alorma.gasstations.di

import com.alorma.gasstations.cache.DatabaseDriverFactory
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual object PlatformModule {
  actual operator fun invoke(): Module = module {
    singleOf(::DatabaseDriverFactory)
  }
}