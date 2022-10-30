package com.markstudio.fuelregistry.feature_app_drawer_menu.presentation

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector


@Composable
fun AppBar(
    title: String = "",
    buttonIcon: ImageVector,
    onButtonClicked: () -> Unit,
    contentDescription: String
) {
    TopAppBar(
        title = {
            Text(
                text = title
            )
        },
        navigationIcon = {
            IconButton(onClick = { onButtonClicked() }) {
                Icon(buttonIcon, contentDescription = contentDescription)
            }
        },
        backgroundColor = MaterialTheme.colors.primary
    )
}
