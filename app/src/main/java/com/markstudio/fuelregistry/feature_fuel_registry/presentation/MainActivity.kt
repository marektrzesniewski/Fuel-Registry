package com.markstudio.fuelregistry.feature_fuel_registry.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.markstudio.fuelregistry.feature_fuel_registry.presentation.add_edit_refuel.components.AddEditRefuelScreen
import com.markstudio.fuelregistry.feature_fuel_registry.presentation.refuels.components.RefuelsScreen
import com.markstudio.fuelregistry.feature_fuel_registry.presentation.util.Screen
import com.markstudio.fuelregistry.ui.theme.RefuelAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RefuelAppTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.RefuelsScreen.route
                    ) {
                        composable(route = Screen.RefuelsScreen.route) {
                            RefuelsScreen(navController = navController)
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
//                            RefuelsScreen(navController = navController)
                            AddEditRefuelScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}
