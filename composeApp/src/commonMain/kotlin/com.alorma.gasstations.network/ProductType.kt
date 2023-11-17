package com.alorma.gasstations.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductType(
  @SerialName("IDProducto")
  val IDProducto: String,
  @SerialName("NombreProducto")
  val NombreProducto: String,
  @SerialName("NombreProductoAbreviatura")
  val NombreProductoAbreviatura: String,
)

/*
    {
        "IDProducto": "18",
        "NombreProducto": "Gas natural comprimido",
        "NombreProductoAbreviatura": "GNC"
    }
 */
