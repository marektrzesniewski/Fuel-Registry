package com.markstudio.fuelregistry.feature_fuel_registry.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.markstudio.fuelregistry.feature_app_drawer_menu.presentation.DrawerBody
import com.markstudio.fuelregistry.feature_app_drawer_menu.presentation.DrawerHeader
import com.markstudio.fuelregistry.feature_fuel_registry.presentation.add_edit_refuel.components.AddEditRefuelScreen
import com.markstudio.fuelregistry.feature_fuel_registry.presentation.refuels.components.RefuelsScreen
import com.markstudio.fuelregistry.feature_fuel_registry.presentation.util.Screen
import com.markstudio.fuelregistry.feature_vehicles.presentation.add_edit_vehicle.components.AddEditCarScreen
import com.markstudio.fuelregistry.feature_vehicles.presentation.vehicles.components.CarsScreen
import com.markstudio.fuelregistry.ui.theme.RefuelAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RefuelAppTheme {
                AppMainScreen()
            }
        }
    }
}

@Composable
fun AppMainScreen() {
    val navController = rememberNavController()
    Surface(color = MaterialTheme.colors.background) {
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        val openDrawer = {
            scope.launch {
                drawerState.open()
            }
        }
        ModalDrawer(
            drawerState = drawerState,
            gesturesEnabled = drawerState.isOpen,
            drawerContent = {
                DrawerHeader()
                DrawerBody(
                    onDestinationClicked = { route ->
                        scope.launch {
                            drawerState.close()
                        }
                        navController.navigate(route) {
                            popUpTo = navController.graph.startDestinationId
                            launchSingleTop = true
                        }
                    }
                )
            }
        ) {
            NavHost(
                navController = navController,
                startDestination = Screen.RefuelsScreen.route
            ) {
                composable(route = Screen.RefuelsScreen.route) {
                    RefuelsScreen(
                        navController = navController,
                        openDrawer = { openDrawer() }
                    )
                }

                composable(route = Screen.AddEditRefuelScreen.route +
                        "?refuelId={refuelId}",
                    arguments = listOf(
                        navArgument(
                            name = "refuelId"
                        ) {
                            type = NavType.IntType
                            defaultValue = -1
                        }
                    )
                ) {
                    AddEditRefuelScreen(navController = navController)
                }

                composable(route = Screen.CarsScreen.route) {
                    CarsScreen(
                        navController = navController,
                        openDrawer = { openDrawer() }
                    )
                }
                
                composable(route = Screen.AddEditCarScreen.route +
                        "?carId={carId}",
                    arguments = listOf(
                        navArgument(
                            name = "carId"
                        ) {
                            type = NavType.IntType
                            defaultValue = -1
                        }
                    )
                ) {
                    AddEditCarScreen(navController = navController)
                }
            }
        }
    }
}
