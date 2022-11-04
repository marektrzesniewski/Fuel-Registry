package com.markstudio.fuelregistry.feature_vehicles.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.markstudio.fuelregistry.feature_fuel_registry.domain.model.Refuel
import com.markstudio.fuelregistry.feature_vehicles.domain.model.Car
import kotlinx.coroutines.flow.Flow

@Dao
interface CarDao {

    @Query("SELECT * FROM car")
    fun getCars(): Flow<List<Car>>

    @Query("SELECT * FROM car WHERE id = :id")
    suspend fun getCarById(id: Int): Car?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCar(car: Car)

    @Delete
    suspend fun deleteCar(car: Car)
}