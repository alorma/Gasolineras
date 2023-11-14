package com.alorma.gasstations.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Price(
  @SerialName("biodiesel")
  val biodiesel: String,
  @SerialName("bioethanol")
  val bioethanol: String,
  @SerialName("compressedNaturalGas")
  val compressedNaturalGas: String,
  @SerialName("liquefiedNaturalGas")
  val liquefiedNaturalGas: String,
  @SerialName("liquefiedPetroleumGases")
  val liquefiedPetroleumGases: String,
  @SerialName("dieselA")
  val dieselA: String,
  @SerialName("dieselB")
  val dieselB: String,
  @SerialName("premiumDiesel")
  val premiumDiesel: String,
  @SerialName("gasoline95E10")
  val gasoline95E10: String,
  @SerialName("gasoline95E5")
  val gasoline95E5: String,
  @SerialName("premiumGasoline95E5")
  val premiumGasoline95E5: String,
  @SerialName("gasoline98E10")
  val gasoline98E10: String,
  @SerialName("gasoline98E5")
  val gasoline98E5: String,
  @SerialName("hydrogen")
  val hydrogen: String
)
