package com.alorma.gasstations.products.data

import com.alorma.gasstations.cache.Database
import com.alorma.gasstations.network.ProductType
import com.alorma.gasstations.products.domain.ProductsDataSource

class ProductsDataSourceImpl(
  private val database: Database,
  private val api: ProductsApi,
) : ProductsDataSource {
  override suspend fun getAllProducts(): List<ProductType> {
    return database.getAllProducts().ifEmpty {
      api.getAllProducts().also { products ->
        database.insertProducts(products)
      }
    }
  }

  override suspend fun updateProduct(productType: ProductType) {
    database.updateProduct(productType)
  }
}