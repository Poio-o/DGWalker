package com.alanturing.dgwalker.features.items.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ItemsScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToDigimon: () -> Unit,
    onNavigateToExchange: () -> Unit
) {
    Column {
        Text("Inventario de Objetos")

        Button(onClick = onNavigateToHome) { Text("Ir a Home") }
        Button(onClick = onNavigateToDigimon) { Text("Ir a Digimon") }
        Button(onClick = onNavigateToExchange) { Text("Ir a Intercambio") }
    }
}
