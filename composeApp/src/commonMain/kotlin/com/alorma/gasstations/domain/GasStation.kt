package com.alorma.gasstations.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GasStation(
  @SerialName("postalCode")
  val postalCode: String,
  @SerialName("address")
  val address: String,
  @SerialName("schedule")
  val schedule: String,
  @SerialName("Rótulo")
  val name: String,
  @SerialName("locality")
  val locality: String,
  @SerialName("margin")
  val margin: String,
  @SerialName("municipality")
  val municipality: String,
  @SerialName("province")
  val province: String,
  @SerialName("remission")
  val remission: String,
  @SerialName("sign")
  val sign: String,
  @SerialName("saleType")
  val saleType: String,
  @SerialName("ideess")
  val ideess: String,
  @SerialName("municipalityId")
  val municipalityId: String,
  @SerialName("provinceId")
  val provinceId: String,
  @SerialName("ccaaId")
  val ccaaId: String,
)