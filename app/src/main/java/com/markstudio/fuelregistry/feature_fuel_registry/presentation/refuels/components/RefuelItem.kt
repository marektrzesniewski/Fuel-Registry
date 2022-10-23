package com.markstudio.fuelregistry.feature_fuel_registry.presentation.refuels.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.markstudio.fuelregistry.feature_fuel_registry.domain.model.Refuel


@Composable
fun RefuelItem(
    refuel: Refuel,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 10.dp,
    onDeleteClick: () -> Unit
) {

    Box(modifier = modifier) {

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {

            Text(
                text = refuel.timestamp.toString(),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "amount: " + refuel.amount.toString(),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "price: " + refuel.pricePerLiter.toString(),
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RefuelItem(refuel = Refuel(456456, 7.3, 45.8, 12)) {
    }
}