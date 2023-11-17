package com.alorma.gasstations.products.data.di

import com.alorma.gasstations.products.data.ProductsApi
import com.alorma.gasstations.products.data.ProductsDataSourceImpl
import com.alorma.gasstations.products.domain.ProductsDataSource
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

object ProductsDataModule {
  operator fun invoke() = module {
    factoryOf(::ProductsApi)
    factoryOf(::ProductsDataSourceImpl) bind ProductsDataSource::class
  }
}