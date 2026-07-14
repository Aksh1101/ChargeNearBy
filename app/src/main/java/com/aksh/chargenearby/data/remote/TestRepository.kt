package com.aksh.chargenearby.data.remote



import android.util.Log
import com.aksh.chargenearby.data.remote.api.OpenChargeMapApi
import javax.inject.Inject

class TestRepository @Inject constructor(
    private val api: OpenChargeMapApi
) {

    suspend fun test(
        latitude: Double,
        longitude: Double
    ) {

        val response = api.getNearbyStations(
            latitude,
            longitude
        )

        Log.d(
            "OpenChargeMap",
            "Stations returned = ${response.size}"
        )

        response.take(5).forEach {

            Log.d(
                "OpenChargeMap",
                it.toString()
            )
        }
    }
}