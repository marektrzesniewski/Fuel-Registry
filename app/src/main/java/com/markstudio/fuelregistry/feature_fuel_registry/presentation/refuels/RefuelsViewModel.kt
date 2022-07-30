package com.markstudio.fuelregistry.feature_fuel_registry.presentation.refuels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.markstudio.fuelregistry.feature_fuel_registry.domain.model.Refuel
import com.markstudio.fuelregistry.feature_fuel_registry.domain.use_case.RefuelUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RefuelsViewModel @Inject constructor(
    private val refuelUseCases: RefuelUseCases
) : ViewModel() {

    private val _state = mutableStateOf(RefuelsState())
    val state: State<RefuelsState> = _state

    private var recentlyDeletedRefuel: Refuel? = null

    private var getRefuelsJob: Job? = null

    init {
        getRefuels()
    }


    fun onEvent(event: RefuelsEvent) {
        when (event) {
            is RefuelsEvent.DeleteRefuel -> {
                viewModelScope.launch {
                    refuelUseCases.deleteRefuel(event.refuel)
                    recentlyDeletedRefuel = event.refuel
                }
            }
            is RefuelsEvent.RestoreRefuel -> {
                viewModelScope.launch {
                    refuelUseCases.addRefuel(recentlyDeletedRefuel ?: return@launch)
                    recentlyDeletedRefuel = null
                }
            }

        }
    }

    private fun getRefuels() {
        getRefuelsJob?.cancel()
        getRefuelsJob = refuelUseCases.getRefuels()
            .onEach { refuels ->
                _state.value = state.value.copy(
                    refuels = refuels
                )
            }
            .launchIn(viewModelScope)
    }
}