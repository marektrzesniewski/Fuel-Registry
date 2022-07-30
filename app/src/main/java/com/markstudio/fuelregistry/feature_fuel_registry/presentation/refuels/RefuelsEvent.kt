package com.markstudio.fuelregistry.feature_fuel_registry.presentation.refuels

import com.markstudio.fuelregistry.feature_fuel_registry.domain.model.Refuel

sealed class RefuelsEvent {
    data class DeleteRefuel(val refuel: Refuel): RefuelsEvent()
    object RestoreRefuel: RefuelsEvent()
}
