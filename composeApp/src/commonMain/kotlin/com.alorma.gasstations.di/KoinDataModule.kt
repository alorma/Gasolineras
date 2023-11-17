package com.alorma.gasstations.di

import com.alorma.gasstations.cache.AppDatabase
import com.alorma.gasstations.cache.Database
import com.alorma.gasstations.cache.DatabaseDriverFactory
import com.alorma.gasstations.domain.GasStationsSdk
import com.alorma.gasstations.network.GasStationsApi
import com.russhwolf.settings.Settings
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

object KoinDataModule {
  operator fun invoke() = module {
    factoryOf(::Settings)
    single {
      AppDatabase(get<DatabaseDriverFactory>().createDriver())
    }
    single {
      get<AppDatabase>().appDatabaseQueries
    }
    factoryOf(::Database)
    factory {
      HttpClient {
        install(ContentNegotiation) {
          json(
            Json {
              ignoreUnknownKeys = true
              useAlternativeNames = false
            }
          )
        }
        install(Logging) {
          logger = object : Logger {
            override fun log(message: String) {
              Napier.d { message }
            }
          }
          level = LogLevel.HEADERS
        }
        defaultRequest {
          url(
            "https://sedeaplicaciones.minetur.gob.es/ServiciosRESTCarburantes/"
          )
        }
      }
    }
    factoryOf(::GasStationsApi)
    factoryOf(::GasStationsSdk)
  }
}