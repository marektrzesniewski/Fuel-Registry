package com.markstudio.fuelregistry.feature_fuel_registry.presentation.add_edit_refuel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.markstudio.fuelregistry.feature_fuel_registry.domain.model.InvalidRefuelException
import com.markstudio.fuelregistry.feature_fuel_registry.domain.model.Refuel
import com.markstudio.fuelregistry.feature_fuel_registry.domain.use_case.RefuelUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditRefuelViewModel @Inject constructor(
    private val refuelUseCases: RefuelUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _refuelTimestamp = mutableStateOf(RefuelTimestampFieldState(hint = "Enter time"))
    val refuelTimestamp: State<RefuelTimestampFieldState> = _refuelTimestamp

    private val _pricePerLiter =
        mutableStateOf(RefuelTimestampFieldState(hint = "Enter price per liter"))
    val pricePerLiter: State<RefuelTimestampFieldState> = _pricePerLiter

    private val _amount = mutableStateOf(RefuelTimestampFieldState(hint = "Enter amount"))
    val amount: State<RefuelTimestampFieldState> = _amount

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentRefuelId: Int? = null

    init {
        savedStateHandle.get<Int>("refuelId")?.let { refuelId ->
            if (refuelId != -1) {
                viewModelScope.launch {
                    refuelUseCases.getRefuel(refuelId)?.also { refuel ->
                        currentRefuelId = refuel.id
                        _refuelTimestamp.value = refuelTimestamp.value.copy(
                            text = refuel.timestamp.toString(),
                            isHintVisible = false
                        )
                        _pricePerLiter.value = pricePerLiter.value.copy(
                            text = refuel.pricePerLiter.toString(),
                            isHintVisible = false
                        )
                        _amount.value = amount.value.copy(
                            text = refuel.amount.toString(),
                            isHintVisible = false
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditRefuelEvent) {
        when (event) {
            is AddEditRefuelEvent.EnteredTimestamp -> {
                _refuelTimestamp.value = refuelTimestamp.value.copy(
                    text = event.value
                )
            }
            is AddEditRefuelEvent.ChangeTimestampFocus -> {
                _refuelTimestamp.value = refuelTimestamp.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            refuelTimestamp.value.text.isBlank()
                )
            }
            is AddEditRefuelEvent.EnteredPricePerLiter -> {
                _pricePerLiter.value = pricePerLiter.value.copy(
                    text = event.value
                )
            }
            is AddEditRefuelEvent.ChangePricePerLiterFocus -> {
                _pricePerLiter.value = pricePerLiter.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            refuelTimestamp.value.text.isBlank()
                )
            }
            is AddEditRefuelEvent.EnteredAmount -> {
                _amount.value = amount.value.copy(
                    text = event.value
                )
            }
            is AddEditRefuelEvent.ChangeAmountFocus -> {
                _amount.value = amount.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            refuelTimestamp.value.text.isBlank()
                )
            }
            is AddEditRefuelEvent.SaveRefuel -> {
                viewModelScope.launch {
                    try {
                        refuelUseCases.addRefuel(
                            Refuel(
                                timestamp = refuelTimestamp.value.text.toLong(),
                                pricePerLiter = pricePerLiter.value.text.toDouble(),
                                amount = amount.value.text.toDouble(),
                                id = currentRefuelId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveRefuel)
                    } catch (e: InvalidRefuelException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save refuel"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        object SaveRefuel : UiEvent()
    }

}