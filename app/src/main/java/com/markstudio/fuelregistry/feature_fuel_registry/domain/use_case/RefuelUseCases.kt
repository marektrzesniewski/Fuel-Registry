package com.markstudio.fuelregistry.feature_fuel_registry.domain.use_case

data class RefuelUseCases(
    val getRefuels: GetRefuels,
    val getRefuel: GetRefuel,
    val addRefuel: AddRefuel,
    val deleteRefuel: DeleteRefuel
)
