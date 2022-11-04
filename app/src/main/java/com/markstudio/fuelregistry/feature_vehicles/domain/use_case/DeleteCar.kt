package com.markstudio.fuelregistry.feature_vehicles.domain.use_case

import com.markstudio.fuelregistry.feature_vehicles.domain.model.Car
import com.markstudio.fuelregistry.feature_vehicles.domain.repository.CarRepository

class DeleteCar(
    private val repository: CarRepository
) {

    suspend operator fun invoke(car: Car) {
        repository.deleteCar(car)
    }
}