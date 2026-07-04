package com.aksh.chargenearby

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import org.maplibre.android.MapLibre

@HiltAndroidApp
class ChargeNearByApp : Application(){

    override fun onCreate() {
        super.onCreate()

        MapLibre.getInstance(this)
    }
}

