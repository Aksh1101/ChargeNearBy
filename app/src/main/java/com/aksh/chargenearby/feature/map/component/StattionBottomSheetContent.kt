package com.aksh.chargenearby.feature.map.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.aksh.chargenearby.domain.model.ChargingStation

@Composable
fun StationBottomSheetContent(
    station: ChargingStation,
    onDirectionsClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = 20.dp,
                end = 20.dp,
                bottom = 24.dp
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = station.name,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(4.dp))

                Text(
                    text = station.address,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(Modifier.width(12.dp))

            Text(
                text = stationStatusText(station),
                style = MaterialTheme.typography.labelLarge
            )
        }

        Spacer(Modifier.height(16.dp))

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            station.connectorTypes.forEach { connector ->
                AssistChip(
                    onClick = {},
                    label = {
                        Text(connector.displayName)
                    }
                )
            }
        }

        Spacer(Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            StationInfoItem(
                label = "Available",
                value = "${station.availableConnectors}/${station.totalConnectors}"
            )

            StationInfoItem(
                label = "Speed",
                value = "${station.chargingSpeedKw} kW"
            )

            StationInfoItem(
                label = "Price",
                value = station.pricePerKwh?.let {
                    "₹$it/kWh"
                } ?: "Unavailable"
            )
        }

        Spacer(Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedButton(
                onClick = onSaveClick,
                modifier = Modifier.weight(1f)
            ) {
                Text("Save")
            }

            Button(
                onClick = onDirectionsClick,
                modifier = Modifier.weight(1f)
            ) {
                Text("Directions")
            }
        }
    }
}

private fun stationStatusText(
    station: ChargingStation
): String {
    return when {
        !station.isOperational -> "Offline"
        station.isAvailable -> "Available"
        else -> "Full"
    }
}

@Composable
private fun StationInfoItem(
    label: String,
    value: String
) {
    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium
        )

        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold
        )
    }
}