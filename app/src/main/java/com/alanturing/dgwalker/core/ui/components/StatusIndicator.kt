package com.alanturing.dgwalker.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.alanturing.dgwalker.core.ui.theme.ElectricCyan

@Composable
fun StatusIndicator(
    progress: Float,
    modifier: Modifier = Modifier,
    height: Dp = 10.dp,
    progressColor: Color = ElectricCyan,
    trackColor: Color = Color(0xFF102840),
    cornerRadius: Dp = 5.dp
) {
    val clampedProgress = progress.coerceIn(0f, 1f)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .clip(RoundedCornerShape(cornerRadius))
            .background(trackColor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(clampedProgress)
                .clip(RoundedCornerShape(cornerRadius))
                .background(progressColor)
        )
    }
}
