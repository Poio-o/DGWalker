package com.alanturing.dgwalker.features.exchange.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ExchangeQrScreen(
    onNavigateToConnection: () -> Unit,
    onNavigateToResult: () -> Unit
) {
    Column {
        Text("Escanea este código QR")

        Button(onClick = onNavigateToConnection) { Text("Atrás a Conexión") }
        Button(onClick = onNavigateToResult) { Text("Simular QR Escaneado") }
    }
}
