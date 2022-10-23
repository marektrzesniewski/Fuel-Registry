package com.markstudio.fuelregistry.feature_fuel_registry.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.markstudio.fuelregistry.feature_fuel_registry.domain.model.Refuel

@Database(
    entities = [Refuel::class],
    version = 1,
    exportSchema = false
) //todo set export schema to true
abstract class RefuelDatabase : RoomDatabase() {

    abstract val refuelDao: RefuelDao

    companion object {
        const val DATABASE_NAME = "refuels_db"
    }
}