package com.alorma.gasstations.di

import com.alorma.gasstations.ui.KoinUiModule

val appModules = listOf(
  KoinDataModule(),
  KoinUiModule()
)