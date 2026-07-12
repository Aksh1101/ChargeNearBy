package com.aksh.chargenearby.domain.location

import android.location.Location

interface LocationRepository{
    suspend fun getCurrentLocation(): Location?
}