package com.markstudio.fuelregistry.feature_vehicles.data.repository

import com.markstudio.fuelregistry.feature_vehicles.data.data_source.CarDao
import com.markstudio.fuelregistry.feature_vehicles.domain.model.Car
import com.markstudio.fuelregistry.feature_vehicles.domain.repository.CarRepository
import kotlinx.coroutines.flow.Flow

class CarRepositoryImpl(
    private val dao: CarDao
) : CarRepository {
    override fun getCars(): Flow<List<Car>> {
        return dao.getCars()
    }

    override suspend fun getCarById(id: Int): Car? {
        return dao.getCarById(id)
    }

    override suspend fun insertCar(car: Car) {
        dao.insertCar(car)
    }

    override suspend fun deleteCar(car: Car) {
        dao.deleteCar(car)
    }

}