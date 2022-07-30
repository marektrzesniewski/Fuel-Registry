package com.markstudio.fuelregistry.feature_fuel_registry.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Refuel(
    val timestamp: Long,
    val pricePerLiter: Double,
    val amount: Double,
    @PrimaryKey val id: Int? = null
) {

}

class InvalidRefuelException(message: String) : Exception(message)
