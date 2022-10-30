package com.markstudio.fuelregistry.feature_vehicles.presentation.vehicles.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.markstudio.fuelregistry.feature_app_drawer_menu.presentation.AppBar

@Composable
fun VehiclesScreen(
    openDrawer: () -> Unit,
    navController: NavController
) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        AppBar(
            title = "Vehicles",
            buttonIcon = Icons.Default.Menu,
            onButtonClicked = { openDrawer() },
            contentDescription = "Go to vehicles"
        )
        Text(text = "lorem ipsum")
    }
}