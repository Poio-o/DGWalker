package com.alanturing.dgwalker.features.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.alanturing.dgwalker.features.domain.model.Digimon

@Composable
fun HomeScreen(
    onNavigateToProfile: () -> Unit,
    onNavigateToDigimon: () -> Unit,
    onNavigateToItems: () -> Unit,
    onNavigateToExchange: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val activeDigimon = uiState.team.find { it.isWalking } ?: uiState.team.firstOrNull()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg_pattern),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        if (uiState.isLoading) {
            CircularProgressIndicator(color = ElectricCyan)
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Top Bar con Icono de Perfil a la derecha
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Surface(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .clickable { onNavigateToProfile() },
                        color = CardBackground,
                        border = androidx.compose.foundation.BorderStroke(1.dp, NeonBlueBorder)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                painter = painterResource(id = R.drawable.profile_icon),
                                contentDescription = stringResource(id = R.string.profile),
                                tint = ElectricCyan,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }

                // Seccion Central con Digimon y Plataforma Azul
                if (activeDigimon != null) {
                    MainDigimonSection(activeDigimon)
                } else {
                    Text(
                        text = stringResource(id = R.string.no_digimon_in_team),
                        color = InputPlaceholder,
                        fontSize = 16.sp
                    )
                }

                // Seccion de Pasos DigiSteps
                DigiStepsCard(steps = activeDigimon?.stepsWalked ?: 0)

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun MainDigimonSection(digimon: Digimon) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(240.dp)
        ) {
            // Base/Plataforma circular azul
            Box(
                modifier = Modifier
                    .size(width = 220.dp, height = 90.dp)
                    .align(Alignment.BottomCenter)
                    .clip(CircleShape)
                    .background(Color(0xFF0F3A66))
                    .border(3.dp, Color(0xFF00E5FF), CircleShape)
            )

            // Sprite del digimon
            if (digimon.spriteUrl.isNotEmpty()) {
                AsyncImage(
                    model = digimon.spriteUrl,
                    contentDescription = digimon.nickname,
                    modifier = Modifier
                        .size(180.dp)
                        .padding(bottom = 20.dp),
                    contentScale = ContentScale.Fit
                )
            } else {
                Text(
                    text = "👾",
                    fontSize = 90.sp,
                    modifier = Modifier.padding(bottom = 20.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = digimon.nickname,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Barra de estado (Hambre)
        StatusIndicator(
            progress = digimon.hunger / 100f,
            height = 8.dp,
            progressColor = ElectricCyan,
            trackColor = Color(0xFF0D253A),
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .clip(RoundedCornerShape(4.dp))
        )
    }
}

@Composable
fun DigiStepsCard(steps: Int) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp)),
        color = Color(0x33001830),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0x4400E5FF))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.digisteps),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "$steps",
                fontSize = 38.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White
            )
        }
    }
}
