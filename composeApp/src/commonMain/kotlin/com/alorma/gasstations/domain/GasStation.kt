package com.alorma.gasstations.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GasStation(
  @SerialName("C.P.")
  val postalCode: String,
  @SerialName("Dirección")
  val address: String,
  @SerialName("Horario")
  val schedule: String,
  @SerialName("Localidad")
  val locality: String,
  @SerialName("Margen")
  val margin: String,
  @SerialName("Municipio")
  val municipality: String,
  @SerialName("Provincia")
  val province: String,
  @SerialName("Remisión")
  val remission: String,
  @SerialName("Rótulo")
  val name: String,
  @SerialName("Tipo Venta")
  val saleType: String,
  @SerialName("IDEESS")
  val ideess: String,
  @SerialName("IDMunicipio")
  val municipalityId: String,
  @SerialName("IDProvincia")
  val provinceId: String,
  @SerialName("IDCCAA")
  val ccaaId: String,
)