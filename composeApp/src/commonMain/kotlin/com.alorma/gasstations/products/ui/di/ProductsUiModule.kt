package com.alorma.gasstations.products.ui.di

import com.alorma.gasstations.products.ui.ProductsListViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

object ProductsUiModule {
  operator fun invoke() = module {
    factoryOf(::ProductsListViewModel)
  }
}