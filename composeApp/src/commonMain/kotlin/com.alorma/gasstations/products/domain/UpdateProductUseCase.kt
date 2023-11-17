package com.alorma.gasstations.products.domain

import com.alorma.gasstations.network.ProductType

class UpdateProductUseCase(
  private val productsDataSource: ProductsDataSource
) {
  suspend fun updateProduct(productType: ProductType) {
    productsDataSource.updateProduct(productType)
  }
}