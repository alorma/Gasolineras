package com.alorma.gasstations.products.domain

import com.alorma.gasstations.network.ProductType

class ObtainAllGasProductsUseCase(
  private val productsDataSource: ProductsDataSource
) {
  suspend fun getAllProducts(): List<ProductType> {
    return productsDataSource.getAllProducts()
  }
}