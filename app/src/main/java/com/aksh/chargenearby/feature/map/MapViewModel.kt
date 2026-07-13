package com.aksh.chargenearby.feature.map

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aksh.chargenearby.domain.location.LocationRepository
import com.aksh.chargenearby.domain.model.ChargingStation
import com.aksh.chargenearby.domain.repository.ChargingStationRepository
import com.aksh.chargenearby.feature.map.event.MapUiEvent
import com.aksh.chargenearby.feature.map.state.MapUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val locationRepository: LocationRepository,
    private val chargingStationRepository: ChargingStationRepository
): ViewModel(){

    private val _uiState = MutableStateFlow(MapUiState())
    val uiState: StateFlow<MapUiState> = _uiState.asStateFlow()
    private val _events = MutableSharedFlow<MapUiEvent>()
    val events = _events.asSharedFlow()


    fun loadCurrentLocation() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    errorMessage = null,
                    isPermissionDenied = false
                )
            }

            try {
                val location = locationRepository.getCurrentLocation()

                if (location == null) {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = "Current location is unavailable"
                        )
                    }

                    return@launch
                }

                val stations = chargingStationRepository.getNearbyStations(
                    latitude = location.latitude,
                    longitude = location.longitude
                )

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        latitude = location.latitude,
                        longitude = location.longitude,
                        stations = stations,
                        errorMessage = null
                    )
                }
            } catch (exception: SecurityException) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        isPermissionDenied = true,
                        errorMessage = "Location permission is required to find nearby charging stations"
                    )
                }
            } catch (exception: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = exception.message
                            ?: "Unable to load nearby charging stations"
                    )
                }
            }
        }
    }

    fun selectStation(station: ChargingStation) {
        _uiState.update {
            it.copy(selectedStation = station)
        }
    }

    fun clearSelectedStation() {
        _uiState.update {
            it.copy(selectedStation = null)
        }
    }

    fun onDirectionsClick(station: ChargingStation) {
        viewModelScope.launch {
            _events.emit(
                MapUiEvent.OpenDirections(station)
            )
        }
    }

    fun onSaveStationClick(station: ChargingStation) {
        viewModelScope.launch {
            _events.emit(
                MapUiEvent.ShowMessage(
                    message = "${station.name} will be saved in the bookmarks sprint"
                )
            )
        }
    }

    fun loadMapDataIfNeeded() {
        val currentState = _uiState.value
        if (currentState.isLoading) {
            return
        }

        if (
            _uiState.value.latitude != null &&
            _uiState.value.longitude != null &&
            _uiState.value.stations.isNotEmpty()
        ) {
            return
        }

        loadCurrentLocation()
    }

    fun onLocationPermissionResult(isGranted: Boolean) {
        _uiState.update {
            it.copy(
                isPermissionDenied = !isGranted,
                errorMessage = if (isGranted) {
                    null
                } else {
                    "Location permission is required to find nearby charging stations"
                }
            )
        }
    }
}