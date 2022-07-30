package com.markstudio.fuelregistry.feature_fuel_registry.domain.use_case

import com.markstudio.fuelregistry.feature_fuel_registry.domain.model.Refuel
import com.markstudio.fuelregistry.feature_fuel_registry.domain.repository.RefuelRepository

class DeleteRefuel(
    private val repository: RefuelRepository
) {

    suspend operator fun invoke(refuel: Refuel) {
        repository.deleteRefuel(refuel)
    }
}