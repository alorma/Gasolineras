package com.alorma.gasstations.products.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductApiModel(
  @SerialName("IDProducto")
  val id: String,
  @SerialName("NombreProducto")
  val name: String,
  @SerialName("NombreProductoAbreviatura")
  val abbreviation: String,
)