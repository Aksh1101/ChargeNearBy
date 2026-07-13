package com.aksh.chargenearby.feature.map

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aksh.chargenearby.feature.map.component.LocationPermissionHandler
import com.aksh.chargenearby.feature.map.component.MapErrorContent
import com.aksh.chargenearby.feature.map.component.MapLoadingIndicator
import com.aksh.chargenearby.feature.map.component.StationBottomSheetContent
import com.aksh.chargenearby.feature.map.event.MapUiEvent
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberUpdatedMarkerState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(
    viewModel: MapViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()


    var hasLocationPermission by remember { mutableStateOf(false) }

    val cameraPositionState = rememberCameraPositionState()

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false
    )

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    LocationPermissionHandler(
        onPermissionResult = { isGranted ->
            hasLocationPermission = isGranted

            viewModel.onLocationPermissionResult(isGranted)

            if (isGranted){
                viewModel.loadMapDataIfNeeded()
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
    LaunchedEffect(uiState.selectedStation?.id) {
        val station = uiState.selectedStation
            ?: return@LaunchedEffect

        cameraPositionState.animate(
            update = CameraUpdateFactory.newLatLngZoom(
                LatLng(
                    station.latitude - 0.002,
                    station.longitude
                ),
                17f
            ),
            durationMs = 700
        )
    }

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->

            when (event) {
                is MapUiEvent.OpenDirections -> {
                    snackbarHostState.showSnackbar(
                        message = "Directions to ${event.station.name} coming in routing sprint"
                    )
                }

                is MapUiEvent.ShowMessage -> {
                    snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }


    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState
            )
        }
    ){
            innerPadding ->

            Box(modifier = Modifier.fillMaxSize()
                .padding(innerPadding)) {
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
                if (uiState.isLoading) {
                    MapLoadingIndicator()
                }

                uiState.errorMessage?.let { message ->

                    if (uiState.isPermissionDenied) {

                        MapErrorContent(
                            message = message,
                            actionText = "Open Settings",
                            onActionClick = {
                                openAppSettings(context)
                            },
                            modifier = Modifier.align(Alignment.Center)
                        )

                    } else {

                        MapErrorContent(
                            message = message,
                            actionText = "Retry",
                            onActionClick = {
                                viewModel.loadCurrentLocation()
                            },
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }


            }

        }
    uiState.selectedStation?.let { station ->

        ModalBottomSheet(
            onDismissRequest = {
                viewModel.clearSelectedStation()
            },
            sheetState = sheetState
        ) {
            StationBottomSheetContent(
                station = station,
                onSaveClick = {
                    viewModel.onSaveStationClick(station)
                },
                onDirectionsClick = {
                    viewModel.onDirectionsClick(station)
                }
            )
        }
    }

}

private fun openAppSettings(context: Context) {
    val intent = Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts(
            "package",
            context.packageName,
            null
        )
    )

    context.startActivity(intent)
}