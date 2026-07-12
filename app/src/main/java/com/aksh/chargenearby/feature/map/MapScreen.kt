package com.aksh.chargenearby.feature.map

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aksh.chargenearby.feature.map.component.LocationPermissionHandler
import com.aksh.chargenearby.feature.map.component.StationPreviewCard
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberUpdatedMarkerState


@Composable
fun MapScreen(
    viewModel: MapViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var hasLocationPermission by remember { mutableStateOf(false) }

    val cameraPositionState = rememberCameraPositionState()

    LocationPermissionHandler(
        onPermissionResult = { isGranted ->
            hasLocationPermission = isGranted

            if (isGranted){
                viewModel.loadCurrentLocation()
            }
        }
    )

    LaunchedEffect(
        uiState.latitude,
        uiState.longitude
    ) {
        val latitude = uiState.latitude
        val longitude = uiState.longitude

        if (latitude != null && longitude != null) {
            cameraPositionState.animate(
                update = CameraUpdateFactory.newLatLngZoom(
                    LatLng(latitude, longitude),
                    16f
                ),
                durationMs = 1000
            )
        }
    }







    Box(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(
                isMyLocationEnabled = hasLocationPermission
            ),
            uiSettings = MapUiSettings(
                myLocationButtonEnabled = hasLocationPermission,
                zoomControlsEnabled = false
            ),
            onMapClick = {
                viewModel.clearSelectedStation()
            }
        ) {
            uiState.stations.forEach { station ->

                Marker(
                    state = rememberUpdatedMarkerState(
                        position = LatLng(
                            station.latitude,
                            station.longitude
                        )
                    ),
                    title = station.name,
                    snippet = station.address,
                    onClick = {
                        viewModel.selectStation(station)

                        true
                    }
                )
            }
        }
        uiState.selectedStation?.let { station ->

            StationPreviewCard(
                station = station,
                onViewDetailsClick = {
                    // Station Details navigation comes in Sprint 5
                },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
            )

        }
    }

}