package com.aksh.chargenearby.data.remote.api

import com.aksh.chargenearby.data.remote.dto.ChargingStationDto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface OpenChargeMapApi{

    @Headers(
        "User-Agent: ChargeNearBy Android App"
    )

    @GET("poi")
    suspend fun getNearbyStations(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("distance") distance: Int = 25,
        @Query("distanceunit") distanceUnit: String = "KM",
        @Query("maxresults") maxResults: Int = 30
    ): List<ChargingStationDto>

}