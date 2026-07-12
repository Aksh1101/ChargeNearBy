package com.aksh.chargenearby.domain.model

data class ChargingStation(
    val id: String,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val address: String,
    val connectorTypes: List<String>,
    val availableConnectors: Int,
    val totalConnectors: Int,
    val chargingSpeedKw: Double,
    val pricePerKwh: Double?,
    val isOperational: Boolean
)