package com.alorma.gasstations.products.data

import com.alorma.gasstations.network.ProductType
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class ProductsApi(
  private val httpClient: HttpClient
) {
  suspend fun getAllProducts(): List<ProductType> {
    return withContext(Dispatchers.IO) {
      httpClient.get("PreciosCarburantes/Listados/ProductosPetroliferos/").body()
    }
  }
}