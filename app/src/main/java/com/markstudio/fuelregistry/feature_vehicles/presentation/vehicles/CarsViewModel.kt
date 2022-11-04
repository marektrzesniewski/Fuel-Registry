package com.markstudio.fuelregistry.feature_vehicles.presentation.vehicles

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.markstudio.fuelregistry.feature_vehicles.domain.model.Car
import com.markstudio.fuelregistry.feature_vehicles.domain.use_case.CarUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CarsViewModel @Inject constructor(
    private val carUseCases: CarUseCases
) : ViewModel() {

    private val _state = mutableStateOf(CarsState())
    val state: State<CarsState> = _state

    private var recentlyDeletedCar: Car? = null

    private var getCarsJob: Job? = null

    init {
        getCars()
    }


    fun onEvent(event: CarsEvent) {
        when (event) {
            is CarsEvent.DeleteCar -> {
                viewModelScope.launch {
                    carUseCases.deleteCar(event.car)
                    recentlyDeletedCar = event.car
                }
            }
            is CarsEvent.RestoreCar -> {
                viewModelScope.launch {
                    carUseCases.addCar(recentlyDeletedCar ?: return@launch)
                    recentlyDeletedCar = null
                }
            }

        }
    }

    private fun getCars() {
        getCarsJob?.cancel()
        getCarsJob = carUseCases.getCars()
            .onEach { cars ->
                _state.value = state.value.copy(
                    cars = cars
                )
            }
            .launchIn(viewModelScope)
    }
}