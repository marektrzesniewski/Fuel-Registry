package com.markstudio.fuelregistry.feature_vehicles.presentation.add_edit_vehicle

import androidx.compose.ui.focus.FocusState

sealed class AddEditCarEvent {
    data class EnteredName(val value: String) : AddEditCarEvent()
    data class ChangeNameFocus(val focusState: FocusState) : AddEditCarEvent()
    data class EnteredManufacturer(val value: String) : AddEditCarEvent()
    data class ChangeManufacturerFocus(val focusState: FocusState) : AddEditCarEvent()
    data class EnteredModel(val value: String) : AddEditCarEvent()
    data class ChangeModelFocus(val focusState: FocusState) : AddEditCarEvent()
    data class EnteredFuelType(val value: String) : AddEditCarEvent()
    data class ChangeFuelTypeFocus(val focusState: FocusState) : AddEditCarEvent()
    object SaveCar : AddEditCarEvent()
}
