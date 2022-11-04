package com.markstudio.fuelregistry.feature_vehicles.domain.use_case

import com.markstudio.fuelregistry.feature_vehicles.domain.model.Car
import com.markstudio.fuelregistry.feature_vehicles.domain.repository.CarRepository
import kotlinx.coroutines.flow.Flow

class GetCars(
    private val repository: CarRepository
) {

    operator fun invoke(): Flow<List<Car>> {
        return repository.getCars()
    }
}