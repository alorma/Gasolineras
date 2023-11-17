package com.alorma.gasstations.products

import com.alorma.gasstations.products.data.di.ProductsDataModule
import com.alorma.gasstations.products.domain.di.ProductDomainModule
import org.koin.dsl.module

object ProductModules {
  operator fun invoke() = module {
    includes(ProductsDataModule())
    includes(ProductDomainModule())
  }
}