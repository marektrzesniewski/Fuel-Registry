package com.markstudio.fuelregistry.feature_vehicles.domain.use_case

import com.markstudio.fuelregistry.feature_vehicles.domain.model.Car
import com.markstudio.fuelregistry.feature_vehicles.domain.repository.CarRepository

class GetCar(
    private val repository: CarRepository
) {

    suspend operator fun invoke(id: Int): Car? {
        return repository.getCarById(id)
    }
}