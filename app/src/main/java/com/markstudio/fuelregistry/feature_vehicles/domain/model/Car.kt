package com.markstudio.fuelregistry.feature_vehicles.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Car(
    val carName: String,
    val carManufacturer: String,
    val carModel: String,
    val fuelType: String,
    @PrimaryKey val id: Int? = null
)

class InvalidCarException(message: String) : Exception(message)