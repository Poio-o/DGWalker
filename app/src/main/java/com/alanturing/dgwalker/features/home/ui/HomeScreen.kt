package com.alanturing.dgwalker.features.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onNavigateToProfile: () -> Unit,
    onNavigateToDigimon: () -> Unit,
    onNavigateToItems: () -> Unit,
    onNavigateToExchange: () -> Unit
) {
    Column {
        Text("Bienvenido al Home")
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onNavigateToProfile) { Text("Ir a Perfil") }
        Button(onClick = onNavigateToDigimon) { Text("Ir a Digimon") }
        Button(onClick = onNavigateToItems) { Text("Ir a Objetos") }
        Button(onClick = onNavigateToExchange) { Text("Ir a Intercambio (Preparar)") }
    }
}
