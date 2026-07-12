package com.aksh.chargenearby.domain.repository

import com.aksh.chargenearby.domain.model.ChargingStation

interface ChargingStationRepository {

    suspend fun getNearbyStations(
        latitude: Double,
        longitude: Double
    ): List<ChargingStation>
}