package com.alorma.gasstations.domain

import com.alorma.gasstations.cache.Database
import com.alorma.gasstations.cache.DatabaseDriverFactory
import com.alorma.gasstations.core.parseToInstant
import com.alorma.gasstations.network.GasStationsApi
import com.russhwolf.settings.Settings
import kotlinx.datetime.Clock
import kotlinx.datetime.toInstant
import kotlin.time.Duration.Companion.minutes

class GasStationsSdk(
  databaseDriverFactory: DatabaseDriverFactory,
  private val settings: Settings,
) {
  private val database = Database(databaseDriverFactory)
  private val api = GasStationsApi()

  @Throws(Exception::class)
  suspend fun getGasStations(): GasStationsInfo {
    val cachedGasStations = database.getAllGasStations()

    val dateString = settings.getStringOrNull(DateKey)

    val now = Clock.System.now()
    val savedDate = dateString?.toInstant() ?: now

    val futureTime = savedDate.plus(30.minutes)

    val forceReload = futureTime < now

    return if (cachedGasStations.isNotEmpty() && !forceReload) {
      GasStationsInfo(
        gasStations = cachedGasStations,
        freshData = false,
      )
    } else {
      val allGasStations = api.getAllGasStations()
      val date = allGasStations.date

      settings.putString(DateKey, parseToInstant(date).toString())

      val stations = allGasStations.list.also {
        database.clearDatabase()
        database.createGasStations(it)
      }

      GasStationsInfo(
        gasStations = stations,
        freshData = true,
      )
    }
  }

  companion object {
    private const val DateKey = "date_key"
  }
}