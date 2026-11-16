package com.alanturing.dgwalker.features.exchange.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ExchangeConnectionScreen(
    onNavigateToPrepare: () -> Unit,
    onNavigateToConfirmation: () -> Unit,
    onNavigateToQr: () -> Unit
) {
    Column {
        Text("Conectando...")

        Button(onClick = onNavigateToPrepare) { Text("Volver a Preparación") }
        Button(onClick = onNavigateToConfirmation) { Text("Simular Conexión Exitosa (Confirmar)") }
        Button(onClick = onNavigateToQr) { Text("Mostrar mi código QR") }
    }
}
