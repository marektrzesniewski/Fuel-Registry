package com.markstudio.fuelregistry.feature_fuel_registry.presentation.util

sealed class Screen(val route: String, val title: String) {
    object RefuelsScreen : Screen("refuels_screen", "Refuels")
    object AddEditRefuelScreen : Screen("add_edit_refuel_screen", "todo")
    object VehiclesScreen : Screen("vehicles_screen", "Vehicles")
}
