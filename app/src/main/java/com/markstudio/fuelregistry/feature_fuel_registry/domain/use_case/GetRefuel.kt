package com.markstudio.fuelregistry.feature_fuel_registry.domain.use_case

import com.markstudio.fuelregistry.feature_fuel_registry.domain.model.Refuel
import com.markstudio.fuelregistry.feature_fuel_registry.domain.repository.RefuelRepository
import kotlinx.coroutines.flow.Flow

class GetRefuel(
    private val repository: RefuelRepository
) {

    suspend operator fun invoke(id: Int): Refuel? {
        return repository.getRefuelById(id)
    }
}