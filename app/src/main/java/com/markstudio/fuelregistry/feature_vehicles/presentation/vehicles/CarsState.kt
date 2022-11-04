package com.markstudio.fuelregistry.feature_vehicles.presentation.vehicles

import com.markstudio.fuelregistry.feature_vehicles.domain.model.Car

data class CarsState(
    val cars: List<Car> = emptyList()
)
