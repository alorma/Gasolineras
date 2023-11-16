package com.alorma.gasstations.domain

data class GasStationsInfo(
  val gasStations: List<GasStation>,
  val freshData: Boolean
)
