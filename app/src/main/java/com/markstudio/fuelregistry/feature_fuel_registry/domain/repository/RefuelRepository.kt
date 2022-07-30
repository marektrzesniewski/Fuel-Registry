package com.markstudio.fuelregistry.feature_fuel_registry.domain.repository

import com.markstudio.fuelregistry.feature_fuel_registry.domain.model.Refuel
import kotlinx.coroutines.flow.Flow

interface RefuelRepository {

    fun getRefuels(): Flow<List<Refuel>>

    suspend fun getRefuelById(id: Int): Refuel?

    suspend fun insertRefuel(refuel: Refuel)

    suspend fun deleteRefuel(refuel: Refuel)
}