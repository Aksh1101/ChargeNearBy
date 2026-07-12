package com.aksh.chargenearby.data

import com.aksh.chargenearby.domain.model.ChargingStation
import com.aksh.chargenearby.domain.repository.ChargingStationRepository
import javax.inject.Inject

class FakeChargingStationRepository @Inject constructor() :
    ChargingStationRepository {

    override suspend fun getNearbyStations(
        latitude: Double,
        longitude: Double
    ): List<ChargingStation> {

        return listOf(
            ChargingStation(
                id = "station_1",
                name = "ChargeNearBy Fast Hub",
                latitude = latitude + 0.004,
                longitude = longitude + 0.002,
                address = "Central EV Charging Hub",
                connectorTypes = listOf("CCS2", "Type 2"),
                availableConnectors = 3,
                totalConnectors = 4,
                chargingSpeedKw = 60.0,
                pricePerKwh = 18.0,
                isOperational = true
            ),
            ChargingStation(
                id = "station_2",
                name = "GreenVolt Charging Point",
                latitude = latitude - 0.003,
                longitude = longitude + 0.005,
                address = "GreenVolt EV Station",
                connectorTypes = listOf("CCS2"),
                availableConnectors = 1,
                totalConnectors = 2,
                chargingSpeedKw = 50.0,
                pricePerKwh = 16.5,
                isOperational = true
            ),
            ChargingStation(
                id = "station_3",
                name = "EcoCharge Station",
                latitude = latitude + 0.002,
                longitude = longitude - 0.005,
                address = "EcoCharge Network",
                connectorTypes = listOf("Type 2"),
                availableConnectors = 0,
                totalConnectors = 2,
                chargingSpeedKw = 22.0,
                pricePerKwh = null,
                isOperational = true
            )
        )
    }
}