package com.aksh.chargenearby.feature.map.event

import com.aksh.chargenearby.domain.model.ChargingStation

sealed interface MapUiEvent {

    data class OpenDirections(
        val station: ChargingStation
    ) : MapUiEvent

    data class ShowMessage(
        val message: String
    ) : MapUiEvent

}