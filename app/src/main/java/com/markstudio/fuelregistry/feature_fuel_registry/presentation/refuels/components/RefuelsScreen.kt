package com.markstudio.fuelregistry.feature_fuel_registry.presentation.refuels.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.markstudio.fuelregistry.feature_fuel_registry.presentation.refuels.RefuelsEvent
import com.markstudio.fuelregistry.feature_fuel_registry.presentation.refuels.RefuelsViewModel
import com.markstudio.fuelregistry.feature_fuel_registry.presentation.util.Screen
import kotlinx.coroutines.launch


@Composable
fun RefuelsScreen(
    navController: NavController,
    viewModel: RefuelsViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.AddEditRefuelScreen.route)
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add refuel")
            }
        },
        scaffoldState = scaffoldState
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(state.refuels) { refuel ->
                RefuelItem(
                    refuel = refuel,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(
                                Screen.AddEditRefuelScreen.route +
                                        "?refuelId=${refuel.id}"
                            )
                        },
                    onDeleteClick = {
                        viewModel.onEvent(RefuelsEvent.DeleteRefuel(refuel))
                        scope.launch {
                            val result = scaffoldState.snackbarHostState.showSnackbar(
                                message = "Refuel deleted",
                                actionLabel = "Undo"
                            )
                            if (result == SnackbarResult.ActionPerformed) {
                                viewModel.onEvent(RefuelsEvent.RestoreRefuel)
                            }
                        }
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}


