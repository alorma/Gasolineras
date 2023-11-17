package com.alorma.gasstations.ui.gasstationslist

import com.alorma.gasstations.domain.GasStationsInfo
import com.alorma.gasstations.domain.GasStationsSdk
import com.alorma.gasstations.ui.BaseViewModel

class GasStationsListViewModel(
  private val gasStationsSdk: GasStationsSdk
) : BaseViewModel<GasStationsInfo, Unit>() {

  fun onInit() = launch {
    val gasStationsInfo = gasStationsSdk.getGasStations()

    emitSuccess(gasStationsInfo)
  }
}