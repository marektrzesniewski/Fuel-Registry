package com.markstudio.fuelregistry.feature_fuel_registry.domain.use_case

import com.markstudio.fuelregistry.feature_fuel_registry.data.repository.FakeRefuelRepository
import com.markstudio.fuelregistry.feature_fuel_registry.domain.model.Refuel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class GetRefuelsTest {

    private lateinit var getRefuels: GetRefuels
    private lateinit var fakeRepository: FakeRefuelRepository


    @Before
    fun setUp() {
        fakeRepository = FakeRefuelRepository()
        getRefuels = GetRefuels(fakeRepository)

        val refuelsToInsert = mutableListOf<Refuel>()

        refuelsToInsert.add(Refuel(1662037925, 9.01, 12.29))
        refuelsToInsert.add(Refuel(1662815525, 6.02, 32.29))
        refuelsToInsert.add(Refuel(1662907800, 3.58, 52.50))
        refuelsToInsert.add(Refuel(1663080600, 7.95, 44.44))
        refuelsToInsert.add(Refuel(1663282800, 5.55, 41.09))
        refuelsToInsert.add(Refuel(1663284780, 6.12, 25.00))
        refuelsToInsert.add(Refuel(1663426500, 12.00, 2.00))
        refuelsToInsert.add(Refuel(1663566900, 9.99, 39.33))
        refuelsToInsert.add(Refuel(1663844400, 5.87, 50.00))
        refuelsToInsert.add(Refuel(1664017200, 5.88, 19.00))

        runBlocking {
            refuelsToInsert.forEach { fakeRepository.insertRefuel(it) }
        }
    }

    @Test
    fun `All 10 refuels are returned`() = runBlocking {
        val refuels = getRefuels().first()

        assertEquals(10, refuels.size)
    }

}