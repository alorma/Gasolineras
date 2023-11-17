package com.alorma.gasstations.products.domain.di

import com.alorma.gasstations.products.domain.ObtainAllGasProductsUseCase
import com.alorma.gasstations.products.domain.UpdateProductUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

object ProductDomainModule {
  operator fun invoke() = module {
    factoryOf(::ObtainAllGasProductsUseCase)
    factoryOf(::UpdateProductUseCase)
  }
}