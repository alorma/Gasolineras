package com.alorma.gasstations.products.data

import com.alorma.gasstations.cache.Database
import com.alorma.gasstations.network.ProductType
import com.alorma.gasstations.products.domain.ProductsDataSource

class ProductsDataSourceImpl(
  private val database: Database,
  private val api: ProductsApi,
) : ProductsDataSource {
  override suspend fun getAllProducts(): List<ProductType> {
    val cachedProducts = database.getAllProducts()

    return cachedProducts.ifEmpty {
      api.getAllProducts().also { products ->
        database.insertProducts(products)
      }
    }
  }
}