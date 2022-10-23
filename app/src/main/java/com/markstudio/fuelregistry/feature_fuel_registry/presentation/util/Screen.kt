package com.markstudio.fuelregistry.feature_fuel_registry.presentation.util

sealed class Screen(val route: String) {
    object RefuelsScreen : Screen("refuels_screen")
    object AddEditRefuelScreen : Screen("add_edit_refuel_screen")
}
