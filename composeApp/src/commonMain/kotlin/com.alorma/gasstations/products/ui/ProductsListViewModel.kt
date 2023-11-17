package com.alorma.gasstations.products.ui

import com.alorma.gasstations.network.ProductType
import com.alorma.gasstations.products.domain.ObtainAllGasProductsUseCase
import com.alorma.gasstations.products.domain.UpdateProductUseCase
import com.alorma.gasstations.ui.BaseViewModel

class ProductsListViewModel(
  private val obtainAllGasProductsUseCase: ObtainAllGasProductsUseCase,
  private val updateProductUseCase: UpdateProductUseCase,
) : BaseViewModel<List<ProductUiModule>, Unit>() {

  fun onInit() = launch {
    val products = obtainAllGasProductsUseCase.getAllProducts()

    val uiModels = products.map { product ->
      ProductUiModule(
        id = product.id,
        name = product.name,
        abbreviation = product.abbreviation,
        selected = product.selected,
      )
    }
    emitSuccess(uiModels)
  }

  fun onProductSelected(product: ProductUiModule) = launch {
    updateProductUseCase.updateProduct(
      ProductType(
        id = product.id,
        name = product.name,
        abbreviation = product.abbreviation,
        selected = !product.selected,
      )
    )
    updateIfSuccess { currentList ->
      currentList.map { aProduct ->
        if (aProduct.id == product.id) {
          product.copy(selected = !aProduct.selected)
        } else {
          aProduct
        }
      }
    }
  }
}