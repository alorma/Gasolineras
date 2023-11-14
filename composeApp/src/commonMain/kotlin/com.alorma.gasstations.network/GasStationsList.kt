package com.alorma.gasstations.network

import com.alorma.gasstations.domain.GasStation
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GasStationsList(
  @SerialName("Fecha")
  val date: String,
  @SerialName("ListaEESSPrecio")
  val list: List<GasStation>,
)
