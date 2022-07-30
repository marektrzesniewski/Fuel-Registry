package com.markstudio.fuelregistry.feature_fuel_registry.data.repository

import com.markstudio.fuelregistry.feature_fuel_registry.data.data_source.RefuelDao
import com.markstudio.fuelregistry.feature_fuel_registry.domain.model.Refuel
import com.markstudio.fuelregistry.feature_fuel_registry.domain.repository.RefuelRepository
import kotlinx.coroutines.flow.Flow

class RefuelRepositoryImpl(
    private val dao: RefuelDao
) : RefuelRepository {
    override fun getRefuels(): Flow<List<Refuel>> {
        return dao.getRefuels()
    }

    override suspend fun getRefuelById(id: Int): Refuel? {
        return dao.getRefuelById(id)
    }

    override suspend fun insertRefuel(refuel: Refuel) {
        dao.insertNote(refuel)
    }

    override suspend fun deleteRefuel(refuel: Refuel) {
        dao.deleteRefuel(refuel)
    }

}