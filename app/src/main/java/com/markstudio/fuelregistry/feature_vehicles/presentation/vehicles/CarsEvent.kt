package com.markstudio.fuelregistry.feature_vehicles.presentation.vehicles

import com.markstudio.fuelregistry.feature_vehicles.domain.model.Car

sealed class CarsEvent {
    data class DeleteCar(val car: Car) : CarsEvent()
    object RestoreCar : CarsEvent()
}
