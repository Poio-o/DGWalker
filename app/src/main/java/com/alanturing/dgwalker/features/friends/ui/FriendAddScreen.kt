package com.alanturing.dgwalker.features.friends.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun FriendAddScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToDigimon: () -> Unit,
    onNavigateToItems: () -> Unit,
    onNavigateToExchange: () -> Unit,
    onNavigateToFriendList: () -> Unit
) {
    Column {
        Text("Añadir un nuevo amigo")

        Button(onClick = onNavigateToHome) { Text("Ir a Home") }
        Button(onClick = onNavigateToDigimon) { Text("Ir a Digimon") }
        Button(onClick = onNavigateToItems) { Text("Ir a Objetos") }
        Button(onClick = onNavigateToExchange) { Text("Ir a Intercambio") }
        Button(onClick = onNavigateToFriendList) { Text("Volver a Lista de Amigos") }
    }
}
