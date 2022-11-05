package com.markstudio.fuelregistry.feature_vehicles.presentation.vehicles.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.markstudio.fuelregistry.feature_app_drawer_menu.presentation.AppBar
import com.markstudio.fuelregistry.feature_commons.presentation.util.Screen
import com.markstudio.fuelregistry.feature_vehicles.presentation.vehicles.CarsEvent
import com.markstudio.fuelregistry.feature_vehicles.presentation.vehicles.CarsViewModel
import kotlinx.coroutines.launch

@Composable
fun CarsScreen(
    openDrawer: () -> Unit,
    navController: NavController,
    viewModel: CarsViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        AppBar(
            title = "Cars",
            buttonIcon = Icons.Default.Menu,
            onButtonClicked = { openDrawer() },
            contentDescription = "Go to cars"
        )

        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        navController.navigate(Screen.AddEditCarScreen.route)
                    },
                    backgroundColor = MaterialTheme.colors.primary
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add car")
                }
            },
            scaffoldState = scaffoldState
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(state.cars) { car ->
                    CarItem(
                        car = car,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(
                                    Screen.AddEditCarScreen.route +
                                            "?carId=${car.id}"
                                )
                            },
                        onDeleteClick = {
                            viewModel.onEvent(CarsEvent.DeleteCar(car))
                            scope.launch {
                                val result = scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Car deleted",
                                    actionLabel = "Undo"
                                )
                                if (result == SnackbarResult.ActionPerformed) {
                                    viewModel.onEvent(CarsEvent.RestoreCar)
                                }
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }

    }
}