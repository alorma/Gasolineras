package com.alorma.gasstations.cache

import com.alorma.gasstations.domain.GasStation

internal class Database(databaseDriverFactory: DatabaseDriverFactory) {
  private val database = AppDatabase(databaseDriverFactory.createDriver())
  private val dbQuery = database.appDatabaseQueries

  internal fun clearDatabase() {
    dbQuery.transaction {
      dbQuery.removeAllLaunches()
    }
  }

  internal fun getAllGasStations(): List<GasStation> {
    return dbQuery.selectAllLaunchesInfo(::mapGasStations).executeAsList()
  }

  private fun mapGasStations(
    postalCode: String?,
    address: String?,
    schedule: String?,
    locality: String?,
    margin: String?,
    municipality: String?,
    province: String?,
    remission: String?,
    sign: String?,
    saleType: String?,
    ideess: String?,
    municipalityId: String?,
    provinceId: String?,
    ccaaId: String?,
    name: String?,
  ): GasStation {
    return GasStation(
      postalCode = postalCode.orEmpty(),
      address = address.orEmpty(),
      schedule = schedule.orEmpty(),
      locality = locality.orEmpty(),
      margin = margin.orEmpty(),
      municipality = municipality.orEmpty(),
      province = province.orEmpty(),
      remission = remission.orEmpty(),
      sign = sign.orEmpty(),
      saleType = saleType.orEmpty(),
      ideess = ideess.orEmpty(),
      municipalityId = municipalityId.orEmpty(),
      provinceId = provinceId.orEmpty(),
      name = name.orEmpty(),
      ccaaId = ccaaId.orEmpty(),
    )
  }

  internal fun createGasStations(gasStations: List<GasStation>) {
    dbQuery.transaction {
      gasStations.forEach { gasStation ->
        insertLaunch(gasStation)
      }
    }
  }

  private fun insertLaunch(gasStation: GasStation) {
    dbQuery.insertLaunch(
      postalCode = gasStation.postalCode,
      address = gasStation.address,
      schedule = gasStation.schedule,
      locality = gasStation.locality,
      margin = gasStation.margin,
      municipality = gasStation.municipality,
      province = gasStation.province,
      remission = gasStation.remission,
      sign = gasStation.sign,
      saleType = gasStation.saleType,
      ideess = gasStation.ideess,
      municipalityId = gasStation.municipalityId,
      provinceId = gasStation.provinceId,
      ccaaId = gasStation.ccaaId,
      name = gasStation.name,
    )
  }
}