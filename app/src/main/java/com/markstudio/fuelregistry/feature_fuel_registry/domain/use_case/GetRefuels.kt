package com.markstudio.fuelregistry.feature_fuel_registry.domain.use_case

import com.markstudio.fuelregistry.feature_fuel_registry.domain.model.Refuel
import com.markstudio.fuelregistry.feature_fuel_registry.domain.repository.RefuelRepository
import kotlinx.coroutines.flow.Flow

class GetRefuels(
    private val repository: RefuelRepository
) {

    operator fun invoke(): Flow<List<Refuel>> {
        return repository.getRefuels()
    }
}