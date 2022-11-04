package com.markstudio.fuelregistry.feature_vehicles.domain.use_case

data class CarUseCases(
    val getCars: GetCars,
    val getCar: GetCar,
    val addCar: AddCar,
    val deleteCar: DeleteCar
)
