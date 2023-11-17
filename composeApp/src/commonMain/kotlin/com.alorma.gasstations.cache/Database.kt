package com.alorma.gasstations.cache

import com.alorma.gasstations.domain.GasStation
import com.alorma.gasstations.network.ProductType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class Database(databaseDriverFactory: DatabaseDriverFactory) {
  private val database = AppDatabase(databaseDriverFactory.createDriver())
  private val dbQuery = database.appDatabaseQueries

  internal suspend fun clearDatabase() = withContext(Dispatchers.IO) {
    dbQuery.transaction {
      dbQuery.removeAllGasStations()
      dbQuery.removeAllProducts()
    }
  }

  internal suspend fun getAllGasStations(): List<GasStation> = withContext(Dispatchers.IO) {
    dbQuery.selectAllGasStationsInfo(::mapGasStations).executeAsList()
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
      saleType = saleType.orEmpty(),
      ideess = ideess.orEmpty(),
      municipalityId = municipalityId.orEmpty(),
      provinceId = provinceId.orEmpty(),
      name = sign.orEmpty(),
      ccaaId = ccaaId.orEmpty(),
    )
  }

  internal suspend fun createGasStations(gasStations: List<GasStation>) = withContext(Dispatchers.IO) {
    dbQuery.transaction {
      gasStations.forEach { gasStation ->
        insertLaunch(gasStation)
      }
    }
  }

  private fun insertLaunch(gasStation: GasStation) {
    dbQuery.insertGasStation(
      postalCode = gasStation.postalCode,
      address = gasStation.address,
      schedule = gasStation.schedule,
      locality = gasStation.locality,
      margin = gasStation.margin,
      municipality = gasStation.municipality,
      province = gasStation.province,
      remission = gasStation.remission,
      saleType = gasStation.saleType,
      ideess = gasStation.ideess,
      municipalityId = gasStation.municipalityId,
      provinceId = gasStation.provinceId,
      ccaaId = gasStation.ccaaId,
      sign = gasStation.name,
    )
  }

  suspend fun getAllProducts(): List<ProductType> = withContext(Dispatchers.IO) {
    dbQuery.selectAllProducts { id, name, abbreviation, selected ->
      ProductType(
        id = id.orEmpty(),
        name = name.orEmpty(),
        abbreviation = abbreviation.orEmpty(),
        selected = selected != null && selected.toInt() == 1,
      )
    }.executeAsList()
  }

  suspend fun insertProducts(products: List<ProductType>) = withContext(Dispatchers.IO) {
    dbQuery.transaction {
      products.forEach { product ->
        dbQuery.insertProducts(
          productId = product.id,
          name = product.name,
          abbreviaton = product.abbreviation,
          selected = if (product.selected) {
            1
          } else {
            0
          },
        )
      }
    }
  }

  suspend fun updateProduct(productType: ProductType) = withContext(Dispatchers.IO) {
    val value = if (productType.selected) {
      1
    } else {
      0
    }
    dbQuery.updateProductSelectById(value.toLong(), productType.id)
  }
}