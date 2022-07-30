package com.markstudio.fuelregistry.feature_fuel_registry.domain.use_case

import com.markstudio.fuelregistry.feature_fuel_registry.domain.model.InvalidRefuelException
import com.markstudio.fuelregistry.feature_fuel_registry.domain.model.Refuel
import com.markstudio.fuelregistry.feature_fuel_registry.domain.repository.RefuelRepository

class AddRefuel(
    private val repository: RefuelRepository
) {

    @Throws(InvalidRefuelException::class)
    suspend operator fun invoke(refuel: Refuel) {
        if (refuel.amount.isNaN()) {
            throw InvalidRefuelException("The amount of the refuel must be a digit")
        }
        if (refuel.amount < 1.0) {
            throw InvalidRefuelException("The amount of the refuel must be minimal 1.0 liter")
        }

        if (refuel.pricePerLiter.isNaN()) {
            throw InvalidRefuelException("The price of the one petrol liter must be a digit")
        }
        if (refuel.pricePerLiter < 0.1) {
            throw InvalidRefuelException("The price of the one petrol liter must be greater than 0.1")
        }


        repository.insertRefuel(refuel)
    }
}