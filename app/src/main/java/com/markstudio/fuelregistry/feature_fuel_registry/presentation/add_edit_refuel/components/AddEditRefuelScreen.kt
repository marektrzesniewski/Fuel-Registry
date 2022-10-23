package com.markstudio.fuelregistry.feature_fuel_registry.presentation.add_edit_refuel.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.markstudio.fuelregistry.feature_fuel_registry.presentation.add_edit_refuel.AddEditRefuelEvent
import com.markstudio.fuelregistry.feature_fuel_registry.presentation.add_edit_refuel.AddEditRefuelViewModel
import kotlinx.coroutines.flow.collectLatest


@Composable
fun AddEditRefuelScreen(
    navController: NavController,
    viewModel: AddEditRefuelViewModel = hiltViewModel()
) {
    val timestamp = viewModel.refuelTimestamp.value
    val pricePerLiter = viewModel.pricePerLiter.value
    val amount = viewModel.amount.value

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is AddEditRefuelViewModel.UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is AddEditRefuelViewModel.UiEvent.SaveRefuel -> {
                    navController.navigateUp()
                }
            }
        }
    }


    Scaffold(
        floatingActionButton = {

            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(AddEditRefuelEvent.SaveRefuel)
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Save refuel")
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
                text = timestamp.text,
                hint = timestamp.hint,
                onValueChange = {
                    viewModel.onEvent(AddEditRefuelEvent.EnteredTimestamp(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditRefuelEvent.ChangeTimestampFocus(it))
                },
                isHintVisible = timestamp.isHintVisible,
                singleLine = true,
                textStyle = MaterialTheme.typography.body1
            )
            Spacer(modifier = Modifier.height(16.dp))
            TransparentHintTextField(
                text = pricePerLiter.text,
                hint = pricePerLiter.hint,
                onValueChange = {
                    viewModel.onEvent(AddEditRefuelEvent.EnteredPricePerLiter(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditRefuelEvent.ChangePricePerLiterFocus(it))
                },
                isHintVisible = pricePerLiter.isHintVisible,
                singleLine = true,
                textStyle = MaterialTheme.typography.body1
            )
            Spacer(modifier = Modifier.height(16.dp))
            TransparentHintTextField(
                text = amount.text,
                hint = amount.hint,
                onValueChange = {
                    viewModel.onEvent(AddEditRefuelEvent.EnteredAmount(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditRefuelEvent.ChangeAmountFocus(it))
                },
                isHintVisible = amount.isHintVisible,
                singleLine = true,
                textStyle = MaterialTheme.typography.body1
            )
        }
    }
}