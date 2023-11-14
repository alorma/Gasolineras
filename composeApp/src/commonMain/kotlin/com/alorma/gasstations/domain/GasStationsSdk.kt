package com.alorma.gasstations.domain

import com.alorma.gasstations.cache.Database
import com.alorma.gasstations.cache.DatabaseDriverFactory
import com.alorma.gasstations.network.GasStationsApi

class GasStationsSdk(
  databaseDriverFactory: DatabaseDriverFactory
) {
  private val database = Database(databaseDriverFactory)
  private val api = GasStationsApi()

  @Throws(Exception::class)
  suspend fun getGasStations(forceReload: Boolean): List<GasStation> {
    val cachedGasStations = database.getAllGasStations()
    return if (cachedGasStations.isNotEmpty() && !forceReload) {
      cachedGasStations
    } else {
      api.getAllGasStations().also {
        database.clearDatabase()
        database.createGasStations(it)
      }
    }
  }
}