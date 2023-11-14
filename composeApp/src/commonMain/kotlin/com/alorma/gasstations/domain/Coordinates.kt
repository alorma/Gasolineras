package com.alorma.gasstations.domain

data class Coordinates(val latitude: String, val longitude: String)

data class Price(
    val biodiesel: String,
    val bioethanol: String,
    val compressedNaturalGas: String,
    val liquefiedNaturalGas: String,
    val liquefiedPetroleumGases: String,
    val dieselA: String,
    val dieselB: String,
    val premiumDiesel: String,
    val gasoline95E10: String,
    val gasoline95E5: String,
    val premiumGasoline95E5: String,
    val gasoline98E10: String,
    val gasoline98E5: String,
    val hydrogen: String
)

data class Prices(
    val price: Price,
    val bioethanolPercentage: String,
    val methylEsterPercentage: String
)

data class GasStation(
    val postalCode: String,
    val address: String,
    val schedule: String,
    val locality: String,
    val margin: String,
    val municipality: String,
    val province: String,
    val remission: String,
    val sign: String,
    val saleType: String,
    val ideess: String,
    val municipalityId: String,
    val provinceId: String,
    val ccaaId: String,
    val coordinates: Coordinates,
    val prices: Prices
)
