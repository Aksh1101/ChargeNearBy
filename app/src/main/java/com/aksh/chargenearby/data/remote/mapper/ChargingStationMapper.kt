package com.aksh.chargenearby.data.remote.mapper


import com.aksh.chargenearby.data.remote.dto.ChargingStationDto
import com.aksh.chargenearby.domain.model.ChargingStation
import com.aksh.chargenearby.domain.model.ConnectorType

fun ChargingStationDto.toDomain(): ChargingStation {

    val connectorTypes =
        connections
            ?.mapNotNull { connection ->
                connection.connectionType?.title
            }
            ?.map(::mapConnectorType)
            ?.distinct()
            ?: emptyList()

    val maxPower =
        connections
            ?.mapNotNull { it.powerKw }
            ?.maxOrNull()
            ?: 0.0

    return ChargingStation(
        id = id.toString(),

        name = addressInfo.title,

        latitude = addressInfo.latitude,

        longitude = addressInfo.longitude,

        address = addressInfo.address ?: "Address unavailable",

        connectorTypes = connectorTypes,

        // Open Charge Map doesn't provide live availability
        availableConnectors = connections?.size ?: 0,

        totalConnectors = connections?.size ?: 0,

        chargingSpeedKw = maxPower,

        // Not available from this API
        pricePerKwh = null,

        // Assume operational unless proven otherwise
        isOperational = true
    )
}

private fun mapConnectorType(
    type: String
): ConnectorType {

    val value = type.lowercase()

    return when {

        "ccs" in value -> ConnectorType.CCS2

        "type 2" in value -> ConnectorType.TYPE_2

        "chademo" in value -> ConnectorType.CHADEMO

        "gb/t" in value -> ConnectorType.GB_T

        "bharat ac" in value -> ConnectorType.BHARAT_AC_001

        "bharat dc" in value -> ConnectorType.BHARAT_DC_001

        else -> ConnectorType.CCS2
    }
}