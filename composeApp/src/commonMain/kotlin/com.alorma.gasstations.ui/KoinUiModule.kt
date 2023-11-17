package com.alorma.gasstations.ui

import com.alorma.gasstations.ui.gasstationslist.GasStationsListViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

object KoinUiModule {
  operator fun invoke() = module {
    factoryOf(::GasStationsListViewModel)
  }
}