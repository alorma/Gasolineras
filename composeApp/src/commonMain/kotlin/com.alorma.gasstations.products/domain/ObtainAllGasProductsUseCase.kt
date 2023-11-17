package com.alorma.gasstations.products.domain

class ObtainAllGasProductsUseCase(
  private val productsDataSource: ProductsDataSource
) {
  suspend fun getAllProducts() = productsDataSource.getAllProducts()
}