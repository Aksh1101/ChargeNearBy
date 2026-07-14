package com.aksh.chargenearby.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ChargingStationDto(

    @SerializedName("ID")
    val id: Int,

    @SerializedName("AddressInfo")
    val addressInfo: AddressInfoDto,

    @SerializedName("Connections")
    val connections: List<ConnectionDto>?
)

data class AddressInfoDto(

    @SerializedName("Title")
    val title: String,

    @SerializedName("AddressLine1")
    val address: String?,

    @SerializedName("Latitude")
    val latitude: Double,

    @SerializedName("Longitude")
    val longitude: Double
)





data class ConnectionDto(

    @SerializedName("ConnectionType")
    val connectionType: ConnectionTypeDto?,

    @SerializedName("PowerKW")
    val powerKw: Double?
)

data class ConnectionTypeDto(

    @SerializedName("Title")
    val title: String?
)