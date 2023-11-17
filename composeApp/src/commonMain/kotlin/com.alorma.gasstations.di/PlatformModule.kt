package com.alorma.gasstations.di

import org.koin.core.module.Module

expect object PlatformModule {
  operator fun invoke(): Module
}