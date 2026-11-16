package com.alanturing.dgwalker.features.exchange.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ExchangePrepareScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToConnection: () -> Unit
) {
    Column {
        Text("Preparar Intercambio")

        Button(onClick = onNavigateToHome) { Text("Cancelar e ir a Home") }
        Button(onClick = onNavigateToConnection) { Text("Conectar Dispositivo") }
    }
}
