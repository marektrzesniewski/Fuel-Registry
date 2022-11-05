package com.markstudio.fuelregistry.feature_vehicles.presentation.add_edit_vehicle.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.markstudio.fuelregistry.feature_commons.presentation.components.TransparentHintTextField
import com.markstudio.fuelregistry.feature_vehicles.presentation.add_edit_vehicle.AddEditCarEvent
import com.markstudio.fuelregistry.feature_vehicles.presentation.add_edit_vehicle.AddEditCarViewModel
import kotlinx.coroutines.flow.collectLatest


@Composable
fun AddEditCarScreen(
    navController: NavController,
    viewModel: AddEditCarViewModel = hiltViewModel()
) {
    val carName = viewModel.carName.value
    val carManufacturer = viewModel.carManufacturer.value
    val carModel = viewModel.carModel.value


    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is AddEditCarViewModel.UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is AddEditCarViewModel.UiEvent.SaveCar -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(
        floatingActionButton = {

            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(AddEditCarEvent.SaveCar)
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Save car")
            }
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

            }
            TransparentHintTextField(
                text = carName.text,
                hint = carName.hint,
                onValueChange = {
                    viewModel.onEvent(AddEditCarEvent.EnteredName(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditCarEvent.ChangeNameFocus(it))
                },
                isHintVisible = carName.isHintVisible,
                singleLine = true,
                textStyle = MaterialTheme.typography.body1
            )
            Spacer(modifier = Modifier.height(16.dp))
            TransparentHintTextField(
                text = carManufacturer.text,
                hint = carManufacturer.hint,
                onValueChange = {
                    viewModel.onEvent(AddEditCarEvent.EnteredManufacturer(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditCarEvent.ChangeManufacturerFocus(it))
                },
                isHintVisible = carManufacturer.isHintVisible,
                singleLine = true,
                textStyle = MaterialTheme.typography.body1
            )
            Spacer(modifier = Modifier.height(16.dp))
            TransparentHintTextField(
                text = carModel.text,
                hint = carModel.hint,
                onValueChange = {
                    viewModel.onEvent(AddEditCarEvent.EnteredModel(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditCarEvent.ChangeModelFocus(it))
                },
                isHintVisible = carModel.isHintVisible,
                singleLine = true,
                textStyle = MaterialTheme.typography.body1
            )
            Spacer(modifier = Modifier.height(16.dp))
            ExposedDropdownFuelTypeMenu(viewModel)
        }
    }
}

