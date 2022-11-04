package com.markstudio.fuelregistry.feature_fuel_registry.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.markstudio.fuelregistry.feature_fuel_registry.domain.model.Refuel
import com.markstudio.fuelregistry.feature_vehicles.data.data_source.CarDao
import com.markstudio.fuelregistry.feature_vehicles.domain.model.Car

@Database(
    entities = [Refuel::class, Car::class],
    version = 1,
    exportSchema = false
) //todo set export schema to true
abstract class RefuelDatabase : RoomDatabase() {

    abstract val refuelDao: RefuelDao
    abstract val carDao: CarDao

    companion object {
        const val DATABASE_NAME = "refuels_db"
    }
} //todo elevate this file higher