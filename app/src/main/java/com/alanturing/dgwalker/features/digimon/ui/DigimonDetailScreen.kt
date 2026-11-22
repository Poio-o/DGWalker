package com.alanturing.dgwalker.features.digimon.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.alanturing.dgwalker.R
import com.alanturing.dgwalker.core.ui.components.StatusIndicator
import com.alanturing.dgwalker.core.ui.theme.CardBackground
import com.alanturing.dgwalker.core.ui.theme.ElectricCyan
import com.alanturing.dgwalker.core.ui.theme.InputPlaceholder
import com.alanturing.dgwalker.core.ui.theme.NeonBlueBorder
import com.alanturing.dgwalker.features.digimon.ui.components.FeedBottomSheet
import com.alanturing.dgwalker.features.home.ui.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DigimonDetailScreen(
    digimonId: String,
    onNavigateBack: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val team = uiState.team

    var selectedIndex by remember(team, digimonId) {
        val index = team.indexOfFirst { it.id == digimonId }
        mutableStateOf(if (index >= 0) index else 0)
    }

    val currentDigimon = team.getOrNull(selectedIndex)

    var showRenameDialog by remember { mutableStateOf(false) }
    var showFeedSheet by remember { mutableStateOf(false) }
    var newNicknameInput by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg_pattern),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        if (uiState.isLoading || currentDigimon == null) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = ElectricCyan)
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Top Bar con Flecha de Atrás
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        modifier = Modifier
                            .size(44.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .clickable { onNavigateBack() },
                        color = CardBackground,
                        border = androidx.compose.foundation.BorderStroke(1.dp, NeonBlueBorder)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                painter = painterResource(id = R.drawable.arrow_back_icon),
                                contentDescription = stringResource(id = R.string.back),
                                tint = ElectricCyan,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }

                // Seccion Carrusel con Imagen Central y Flechas de Navegación
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(260.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Flecha Izquierda
                    Surface(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .clickable(enabled = selectedIndex > 0) {
                                if (selectedIndex > 0) selectedIndex--
                            },
                        color = if (selectedIndex > 0) CardBackground else Color.Transparent,
                        border = if (selectedIndex > 0) androidx.compose.foundation.BorderStroke(1.dp, NeonBlueBorder) else null
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                painter = painterResource(id = R.drawable.arrow_back_ios_icon),
                                contentDescription = null,
                                tint = if (selectedIndex > 0) ElectricCyan else Color.Transparent,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }

                    // Artwork Grande del Digimon
                    Box(
                        modifier = Modifier.size(220.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        if (currentDigimon.spriteUrl.isNotEmpty()) {
                            AsyncImage(
                                model = currentDigimon.spriteUrl,
                                contentDescription = currentDigimon.nickname,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Fit
                            )
                        } else {
                            Text(
                                text = "👾",
                                fontSize = 80.sp
                            )
                        }
                    }

                    // Flecha Derecha
                    Surface(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .clickable(enabled = selectedIndex < team.size - 1) {
                                if (selectedIndex < team.size - 1) selectedIndex++
                            },
                        color = if (selectedIndex < team.size - 1) CardBackground else Color.Transparent,
                        border = if (selectedIndex < team.size - 1) androidx.compose.foundation.BorderStroke(1.dp, NeonBlueBorder) else null
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                painter = painterResource(id = R.drawable.arrow_forward_ios_icon),
                                contentDescription = null,
                                tint = if (selectedIndex < team.size - 1) ElectricCyan else Color.Transparent,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Nombre y Botón de Editar Apodo
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = currentDigimon.nickname,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = stringResource(id = R.string.edit_nickname),
                        tint = ElectricCyan,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                newNicknameInput = currentDigimon.nickname
                                showRenameDialog = true
                            }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Switch / Control para seleccionar Digimon Activo de Paseo
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp)),
                    color = CardBackground,
                    border = androidx.compose.foundation.BorderStroke(1.dp, NeonBlueBorder)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = R.string.activate_on_home),
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 15.sp
                        )
                        Switch(
                            checked = currentDigimon.isWalking,
                            onCheckedChange = { isChecked ->
                                if (isChecked) {
                                    viewModel.setWalkingDigimon(currentDigimon.id)
                                }
                            },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.White,
                                checkedTrackColor = ElectricCyan,
                                uncheckedThumbColor = InputPlaceholder,
                                uncheckedTrackColor = Color(0xFF0D253A)
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Botón Alimentar Digimon
                Button(
                    onClick = { showFeedSheet = true },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ElectricCyan
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.feed_icon),
                            contentDescription = null,
                            tint = Color(0xFF030D1A),
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = stringResource(id = R.string.feed_digimon),
                            color = Color(0xFF030D1A),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Tarjeta Inferior de Estadísticas del Digimon
                DigimonStatsCard(digimon = currentDigimon)
            }
        }

        // Diálogo Modal para Renombrar Apodo
        if (showRenameDialog && currentDigimon != null) {
            AlertDialog(
                onDismissRequest = { showRenameDialog = false },
                title = { Text(stringResource(id = R.string.edit_nickname), color = Color.White, fontWeight = FontWeight.Bold) },
                text = {
                    Column {
                        Text(stringResource(id = R.string.enter_new_nickname, currentDigimon.speciesName), color = InputPlaceholder)
                        Spacer(modifier = Modifier.height(12.dp))
                        OutlinedTextField(
                            value = newNicknameInput,
                            onValueChange = { newNicknameInput = it },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            viewModel.updateNickname(currentDigimon.id, newNicknameInput)
                            showRenameDialog = false
                        }
                    ) {
                        Text(stringResource(id = R.string.save), color = ElectricCyan, fontWeight = FontWeight.Bold)
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showRenameDialog = false }) {
                        Text(stringResource(id = R.string.cancel), color = InputPlaceholder)
                    }
                },
                containerColor = CardBackground
            )
        }

        // BottomSheet para Alimentar
        if (showFeedSheet && currentDigimon != null) {
            FeedBottomSheet(
                items = uiState.inventory,
                onItemSelect = { item ->
                    viewModel.feedDigimon(currentDigimon.id, item)
                },
                onDismiss = { showFeedSheet = false }
            )
        }
    }
}

@Composable
fun DigimonStatsCard(digimon: com.alanturing.dgwalker.features.domain.model.Digimon) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp)),
        color = CardBackground,
        border = androidx.compose.foundation.BorderStroke(1.dp, NeonBlueBorder)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = stringResource(id = R.string.details_and_status),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(stringResource(id = R.string.species_format, digimon.speciesName), color = InputPlaceholder, fontSize = 14.sp)
                Text(stringResource(id = R.string.level_format, digimon.level), color = ElectricCyan, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(stringResource(id = R.string.generation_format, digimon.generation), color = InputPlaceholder, fontSize = 14.sp)
                Text(stringResource(id = R.string.type_format, digimon.type), color = InputPlaceholder, fontSize = 14.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(stringResource(id = R.string.hunger_label), color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(4.dp))
            StatusIndicator(progress = digimon.hunger / 100f, height = 8.dp)

            Spacer(modifier = Modifier.height(12.dp))

            Text(stringResource(id = R.string.friendship_label), color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(4.dp))
            StatusIndicator(progress = digimon.friendship / 100f, height = 8.dp, progressColor = Color(0xFFFFB74D))
        }
    }
}
