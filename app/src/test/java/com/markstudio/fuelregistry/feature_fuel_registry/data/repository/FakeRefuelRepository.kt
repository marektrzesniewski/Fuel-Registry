package com.markstudio.fuelregistry.feature_fuel_registry.data.repository

import com.markstudio.fuelregistry.feature_fuel_registry.domain.model.Refuel
import com.markstudio.fuelregistry.feature_fuel_registry.domain.repository.RefuelRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRefuelRepository : RefuelRepository {

    private val refuels = mutableListOf<Refuel>()

    override fun getRefuels(): Flow<List<Refuel>> {
        return flow { emit(refuels) }
    }

    override suspend fun getRefuelById(id: Int): Refuel? {
        return refuels.find { it.id == id }
    }

    override suspend fun insertRefuel(refuel: Refuel) {
        refuels.add(refuel)
    }

    override suspend fun deleteRefuel(refuel: Refuel) {
        refuels.remove(refuel)
    }
}