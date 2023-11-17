package com.alorma.gasstations.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductType(
  @SerialName("IDProducto")
  val id: String,
  @SerialName("NombreProducto")
  val name: String,
  @SerialName("NombreProductoAbreviatura")
  val abbreviation: String,
)