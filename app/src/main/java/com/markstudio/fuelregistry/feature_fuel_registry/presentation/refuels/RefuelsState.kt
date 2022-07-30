package com.markstudio.fuelregistry.feature_fuel_registry.presentation.refuels

import com.markstudio.fuelregistry.feature_fuel_registry.domain.model.Refuel

data class RefuelsState(
    val refuels: List<Refuel> = emptyList()
)
