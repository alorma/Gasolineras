package com.alorma.gasstations.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class GasStationsApi(
  private val httpClient: HttpClient,
) {
  suspend fun getGasStationsByCity(): GasStationsList {
    return withContext(Dispatchers.IO) {
      httpClient.get("PreciosCarburantes/EstacionesTerrestres/FiltroMunicipio/1141").body()
    }
  }
}