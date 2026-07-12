package com.aksh.chargenearby.data.repository

import android.annotation.SuppressLint
import android.location.Location
import com.aksh.chargenearby.domain.location.LocationRepository
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.tasks.await

import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val fusedLocationProviderClient : FusedLocationProviderClient
) : LocationRepository {

    @SuppressLint("MissingPermission")
    override suspend fun getCurrentLocation(): Location? {
        return fusedLocationProviderClient.lastLocation.await()
    }

}