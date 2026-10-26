package com.alanturing.dgwalker.features.exchange.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ExchangeConfirmationScreen(
    onNavigateToPrepare: () -> Unit,
    onNavigateToResult: () -> Unit
) {
    Column {
        Text("¿Confirmar el Intercambio?")

        Button(onClick = onNavigateToPrepare) { Text("Rechazar y Volver al inicio") }
        Button(onClick = onNavigateToResult) { Text("Aceptar e Intercambiar") }
    }
}
