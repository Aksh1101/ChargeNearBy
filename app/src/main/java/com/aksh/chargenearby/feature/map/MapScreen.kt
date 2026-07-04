package com.aksh.chargenearby.feature.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.aksh.chargenearby.feature.map.component.MapLibreView

@Composable
fun MapScreen(navController: NavController) {

    MapLibreView(
        modifier = Modifier.fillMaxSize()
    )

}