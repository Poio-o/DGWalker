package com.alanturing.dgwalker.features.settings.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun SettingsScreen(onNavigateToProfile: () -> Unit) {
    Column {
        Text("Configuración / Ajustes")

        Button(onClick = onNavigateToProfile) { Text("Volver al Perfil") }
    }
}
