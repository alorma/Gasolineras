package com.alorma.gasstations.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Prices(
  @SerialName("price")
  val price: Price,
  @SerialName("bioethanolPercentage")
  val bioethanolPercentage: String,
  @SerialName("methylEsterPercentage")
  val methylEsterPercentage: String
)