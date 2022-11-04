package com.markstudio.fuelregistry.feature_app_drawer_menu.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.markstudio.fuelregistry.feature_fuel_registry.presentation.util.Screen
import com.markstudio.fuelregistry.ui.theme.RefuelAppTheme


private val screens = listOf(
    Screen.RefuelsScreen, Screen.CarsScreen
)

@Composable
fun DrawerHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
            .padding(vertical = 44.dp), contentAlignment = Alignment.Center
    ) {
        Text(text = "Header", fontSize = 40.sp)
    }
}


@Composable
fun DrawerBody(
    modifier: Modifier = Modifier, onDestinationClicked: (route: String) -> Unit
) {
    Column(
        modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary)
            .padding(start = 24.dp, top = 48.dp)
    ) {
        screens.forEach { screen ->
            Spacer(Modifier.height(24.dp))
            Text(text = screen.title,
                style = MaterialTheme.typography.h4,
                modifier = Modifier.clickable {
                    onDestinationClicked(screen.route)
                })
        }
    }
}

@Preview
@Composable
fun DrawerPreview() {
    RefuelAppTheme {
        DrawerBody {}
    }
}