package com.alorma.gasstations.products.domain

import com.alorma.gasstations.network.ProductType

interface ProductsDataSource {
  suspend fun getAllProducts(): List<ProductType>
}