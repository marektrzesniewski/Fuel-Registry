package com.markstudio.fuelregistry.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.darkColorScheme
//import androidx.compose.material3.dynamicDarkColorScheme
//import androidx.compose.material3.dynamicLightColorScheme
//import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray


private val DarkColorPalette = darkColors(
    primary = Color.White,
//    background = DarkGray,
    background = Color.White,
    onBackground = Color.Blue,
    surface = LightBlue,
    onSurface = DarkGray
)

@Composable
fun RefuelAppTheme(darkTheme: Boolean = true, content: @Composable() () -> Unit) {
    MaterialTheme(
        colors = DarkColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
