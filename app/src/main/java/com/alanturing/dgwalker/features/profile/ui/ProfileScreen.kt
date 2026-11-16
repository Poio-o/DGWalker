package com.alanturing.dgwalker.features.profile.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ProfileScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToDigimon: () -> Unit,
    onNavigateToItems: () -> Unit,
    onNavigateToExchange: () -> Unit,
    onNavigateToFriendList: () -> Unit,
    onNavigateToSettings: () -> Unit
) {
    Column {
        Text("Perfil de Usuario")

        Button(onClick = onNavigateToHome) { Text("Ir a Home") }
        Button(onClick = onNavigateToDigimon) { Text("Ir a Digimon") }
        Button(onClick = onNavigateToItems) { Text("Ir a Objetos") }
        Button(onClick = onNavigateToExchange) { Text("Ir a Intercambio") }
        Button(onClick = onNavigateToFriendList) { Text("Ver Lista de Amigos") }
        Button(onClick = onNavigateToSettings) { Text("Ir a Ajustes") }
    }
}
