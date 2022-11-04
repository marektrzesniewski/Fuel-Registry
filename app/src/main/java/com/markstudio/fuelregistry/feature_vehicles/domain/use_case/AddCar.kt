package com.markstudio.fuelregistry.feature_vehicles.domain.use_case

import com.markstudio.fuelregistry.feature_vehicles.domain.model.Car
import com.markstudio.fuelregistry.feature_vehicles.domain.model.InvalidCarException
import com.markstudio.fuelregistry.feature_vehicles.domain.repository.CarRepository

class AddCar(
    private val repository: CarRepository
) {
    @Throws(InvalidCarException::class)
    suspend operator fun invoke(car: Car) {

        //todo add terms and conditions for a validation, similiar to AddRefuel.kt

        repository.insertCar(car)
    }
}