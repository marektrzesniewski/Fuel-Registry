package com.markstudio.fuelregistry.feature_vehicles.presentation.add_edit_vehicle.components

import androidx.compose.material.*
import androidx.compose.runtime.*
import com.markstudio.fuelregistry.feature_vehicles.presentation.add_edit_vehicle.AddEditCarEvent
import com.markstudio.fuelregistry.feature_vehicles.presentation.add_edit_vehicle.AddEditCarViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExposedDropdownFuelTypeMenu(
    viewModel: AddEditCarViewModel
) {
    val options = listOf("Pb95", "Pb98", "ON", "LPG")
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        TextField(
            readOnly = true,
            value = viewModel.fuelType.value.text,
            onValueChange = { },
            label = { Text("Choose fuel type") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    onClick = {
                        viewModel.onEvent(AddEditCarEvent.ChosenFuelType(selectionOption))
                        expanded = false
                    }
                ) {
                    Text(text = selectionOption)
                }
            }
        }
    }
}