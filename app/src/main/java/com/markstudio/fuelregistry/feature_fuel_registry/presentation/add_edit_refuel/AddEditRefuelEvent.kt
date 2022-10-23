package com.markstudio.fuelregistry.feature_fuel_registry.presentation.add_edit_refuel

import androidx.compose.ui.focus.FocusState

sealed class AddEditRefuelEvent {
    data class EnteredTimestamp(val value: String) : AddEditRefuelEvent()
    data class ChangeTimestampFocus(val focusState: FocusState) : AddEditRefuelEvent()
    data class EnteredPricePerLiter(val value: String) : AddEditRefuelEvent()
    data class ChangePricePerLiterFocus(val focusState: FocusState) : AddEditRefuelEvent()
    data class EnteredAmount(val value: String) : AddEditRefuelEvent()
    data class ChangeAmountFocus(val focusState: FocusState) : AddEditRefuelEvent()
    object SaveRefuel: AddEditRefuelEvent()
}