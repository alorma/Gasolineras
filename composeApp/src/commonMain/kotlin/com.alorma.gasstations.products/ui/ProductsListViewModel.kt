package com.alorma.gasstations.products.ui

import com.alorma.gasstations.network.ProductType
import com.alorma.gasstations.products.domain.ObtainAllGasProductsUseCase
import com.alorma.gasstations.ui.BaseViewModel

class ProductsListViewModel(
  private val obtainAllGasProductsUseCase: ObtainAllGasProductsUseCase
) : BaseViewModel<List<ProductType>, Unit>() {

  fun onInit() = launch {
    val products = obtainAllGasProductsUseCase.getAllProducts()
    emitSuccess(products)
  }
}