package com.markstudio.fuelregistry.feature_vehicles.presentation.vehicles.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.markstudio.fuelregistry.feature_vehicles.domain.model.Car


@Composable
fun CarItem(
    car: Car,
    modifier: Modifier = Modifier,
    onDeleteClick: () -> Unit
) {

    Box(modifier = modifier) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Text(
                text = "name: " + car.carName,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "manufacturer: " + car.carManufacturer,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "model: " + car.carModel,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "fuel type: " + car.fuelType,
            )
        }
    }
}
