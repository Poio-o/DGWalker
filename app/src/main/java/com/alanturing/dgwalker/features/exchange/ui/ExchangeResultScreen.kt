package com.alanturing.dgwalker.features.exchange.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ExchangeResultScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToDigimon: () -> Unit
) {
    Column {
        Text("¡Intercambio Finalizado con Éxito!")

        Button(onClick = onNavigateToHome) { Text("Terminar e ir al Home") }
        Button(onClick = onNavigateToDigimon) { Text("Ver mis nuevos Digimon") }
    }
}
