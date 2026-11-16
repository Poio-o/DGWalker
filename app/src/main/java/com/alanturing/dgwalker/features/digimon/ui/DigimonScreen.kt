package com.alanturing.dgwalker.features.digimon.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun DigimonScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToItems: () -> Unit,
    onNavigateToExchange: () -> Unit
) {
    Column {
        Text("Colección Digimon")

        Button(onClick = onNavigateToHome) { Text("Ir a Home") }
        Button(onClick = onNavigateToItems) { Text("Ir a Objetos") }
        Button(onClick = onNavigateToExchange) { Text("Ir a Intercambio") }
    }
}
