package com.aksh.chargenearby.data.repository


import com.aksh.chargenearby.data.remote.api.OpenChargeMapApi
import com.aksh.chargenearby.data.remote.mapper.toDomain
import com.aksh.chargenearby.domain.model.ChargingStation
import com.aksh.chargenearby.domain.repository.ChargingStationRepository
import javax.inject.Inject

class ChargingStationRepositoryImpl @Inject constructor(
    private val api: OpenChargeMapApi
) : ChargingStationRepository {

    override suspend fun getNearbyStations(
        latitude: Double,
        longitude: Double
    ): List<ChargingStation> {
        val response = api.getNearbyStations(
            latitude,
            longitude
        )

        android.util.Log.d(
            "OpenChargeMap",
            "Stations received = ${response.size}"
        )

        return response.map {
            it.toDomain()

        }

    }
}