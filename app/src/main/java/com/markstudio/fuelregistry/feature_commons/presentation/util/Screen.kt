package com.markstudio.fuelregistry.feature_commons.presentation.util

sealed class Screen(val route: String, val title: String) {
    object RefuelsScreen : Screen("refuels_screen", "Refuels")
    object AddEditRefuelScreen : Screen("add_edit_refuel_screen", "todo")
    object CarsScreen : Screen("cars_screen", "Cars")
    object AddEditCarScreen : Screen("add_edit_car_screen", "todo")
    object OrlenScreen: Screen("orlen_screen", "Orlen petrol stations")
}
