package com.aksh.chargenearby.feature.map.state

import com.aksh.chargenearby.domain.model.ChargingStation

data class MapUiState(
    val isLoading : Boolean = false,
    val latitude: Double? = null,
    val longitude : Double? = null,
    val errorMessage: String? = null,

    val stations: List<ChargingStation> = emptyList(),
    val selectedStation: ChargingStation? = null,
)