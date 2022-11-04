package com.markstudio.fuelregistry.feature_vehicles.presentation.add_edit_vehicle

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.markstudio.fuelregistry.feature_commons.presentation.TextFieldState
import com.markstudio.fuelregistry.feature_vehicles.domain.model.Car
import com.markstudio.fuelregistry.feature_vehicles.domain.model.InvalidCarException
import com.markstudio.fuelregistry.feature_vehicles.domain.use_case.CarUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditCarViewModel @Inject constructor(
    private val carUseCases: CarUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _carName = mutableStateOf(TextFieldState(hint = "Enter car name"))
    val carName: State<TextFieldState> = _carName

    private val _carManufacturer = mutableStateOf(TextFieldState(hint = "Enter car manufacturer"))
    val carManufacturer: State<TextFieldState> = _carManufacturer

    private val _carModel = mutableStateOf(TextFieldState(hint = "Enter car model"))
    val carModel: State<TextFieldState> = _carModel

    private val _fuelType = mutableStateOf(TextFieldState(hint = "Enter fuel type"))
    val fuelType: State<TextFieldState> = _fuelType

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentCarId: Int? = null

    init {
        println("testing hard")
        println(savedStateHandle.get<Int>("carId"))
        savedStateHandle.get<Int>("carId")?.let{ carId ->
            if (carId != -1) {
                viewModelScope.launch {
                    carUseCases.getCar(carId)?.also { car ->
                    currentCarId = car.id
                        _carName.value = carName.value.copy(
                            text = car.carName,
                            isHintVisible = false
                        )
                        _carManufacturer.value = carManufacturer.value.copy(
                            text = car.carManufacturer,
                            isHintVisible = false
                        )
                        _carModel.value = carModel.value.copy(
                            text = car.carModel,
                            isHintVisible = false
                        )
                        _fuelType.value = fuelType.value.copy(
                            text = car.fuelType,
                            isHintVisible = false
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditCarEvent) {
        when (event) {
            is AddEditCarEvent.EnteredName -> {
                _carName.value = carName.value.copy(
                    text = event.value
                )
            }
            is AddEditCarEvent.ChangeNameFocus -> {
                _carName.value = carName.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            carName.value.text.isBlank()
                )
            }

            is AddEditCarEvent.EnteredManufacturer -> {
                _carManufacturer.value = carManufacturer.value.copy(
                    text = event.value
                )
            }
            is AddEditCarEvent.ChangeManufacturerFocus -> {
                _carManufacturer.value = carManufacturer.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            carManufacturer.value.text.isBlank()
                )
            }

            is AddEditCarEvent.EnteredModel -> {
                _carModel.value = carModel.value.copy(
                    text = event.value
                )
            }
            is AddEditCarEvent.ChangeModelFocus -> {
                _carModel.value = carModel.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            carModel.value.text.isBlank()
                )
            }

            is AddEditCarEvent.EnteredFuelType -> {
                _fuelType.value = fuelType.value.copy(
                    text = event.value
                )
            }
            is AddEditCarEvent.ChangeFuelTypeFocus -> {
                _fuelType.value = fuelType.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            fuelType.value.text.isBlank()
                )
            }
            is AddEditCarEvent.SaveCar -> {
                viewModelScope.launch {
                    try {
                        carUseCases.addCar(
                            Car(
                                carName =  carName.value.text,
                                carManufacturer = carManufacturer.value.text,
                                carModel = carModel.value.text,
                                fuelType = fuelType.value.text,
                                id = currentCarId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveCar)
                    } catch (e: InvalidCarException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save car"
                            )
                        )
                    }
                }
            }

        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        object SaveCar : UiEvent()
    }
}