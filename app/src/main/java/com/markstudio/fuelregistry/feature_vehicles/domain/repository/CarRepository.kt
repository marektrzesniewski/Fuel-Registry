package com.markstudio.fuelregistry.feature_vehicles.domain.repository

import com.markstudio.fuelregistry.feature_vehicles.domain.model.Car
import kotlinx.coroutines.flow.Flow

interface CarRepository {

    fun getCars(): Flow<List<Car>>

    suspend fun getCarById(id: Int): Car?

    suspend fun insertCar(car: Car)

    suspend fun deleteCar(car: Car)

}