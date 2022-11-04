package com.markstudio.fuelregistry.feature_fuel_registry.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.markstudio.fuelregistry.feature_fuel_registry.domain.model.Refuel
import kotlinx.coroutines.flow.Flow

@Dao
interface RefuelDao {

    @Query("SELECT * FROM refuel")
    fun getRefuels(): Flow<List<Refuel>>

    @Query("SELECT * FROM refuel WHERE id = :id")
    suspend fun getRefuelById(id: Int): Refuel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRefuel(refuel: Refuel)

    @Delete
    suspend fun deleteRefuel(refuel: Refuel)
}