package com.alorma.gasstations.products

import com.alorma.gasstations.products.data.di.ProductsDataModule
import com.alorma.gasstations.products.domain.di.ProductDomainModule
import com.alorma.gasstations.products.ui.di.ProductsUiModule
import org.koin.dsl.module

object ProductModules {
  operator fun invoke() = module {
    includes(ProductsDataModule())
    includes(ProductDomainModule())
    includes(ProductsUiModule())
  }
}