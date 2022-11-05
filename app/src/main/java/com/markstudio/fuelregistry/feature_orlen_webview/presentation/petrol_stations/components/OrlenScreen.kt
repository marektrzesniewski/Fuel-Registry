package com.markstudio.fuelregistry.feature_orlen_webview.presentation.petrol_stations.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.markstudio.fuelregistry.feature_app_drawer_menu.presentation.AppBar
import com.markstudio.fuelregistry.feature_commons.presentation.components.WebPageScreen

@Composable
fun OrlenScreen(
    openDrawer: () -> Unit
) {

    val orlenPetrolStations = "https://www.orlen.pl/pl/dla-ciebie/stacje?kw=&from=&to=&s=&wp=&dst=0"

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        AppBar(
            title = "Orlen petrol stations",
            buttonIcon = Icons.Default.Menu,
            onButtonClicked = { openDrawer() },
            contentDescription = "Go to orlen petrol stations"
        )

        WebPageScreen(url = orlenPetrolStations)
    }
}