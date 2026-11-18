package com.alanturing.dgwalker.core.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alanturing.dgwalker.R
import com.alanturing.dgwalker.core.navigation.Routes
import com.alanturing.dgwalker.core.ui.theme.BottomNavBackground
import com.alanturing.dgwalker.core.ui.theme.DGWalkerTheme
import com.alanturing.dgwalker.core.ui.theme.ElectricCyan
import com.alanturing.dgwalker.core.ui.theme.InputPlaceholder

enum class BottomNavItem(val route: String, @param:DrawableRes val iconRes: Int, val contentDescription: String) {
    HOME(Routes.Home.route, R.drawable.home_icon, "Home"),
    DIGIMON(Routes.Digimon.route, R.drawable.grid_icon, "Digimon"),
    ITEMS(Routes.Items.route, R.drawable.backpack_icon, "Items"),
    EXCHANGE(Routes.ExchangePrepare.route, R.drawable.trading_icon, "Exchange")
}

@Composable
fun BottomNavBar(
    selectedItem: BottomNavItem,
    onItemSelected: (BottomNavItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)),
        color = BottomNavBackground
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(horizontal = 24.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomNavItem.entries.forEach { item ->
                val isSelected = item == selectedItem

                val itemBg = if (isSelected) Color(0xFF0F3150) else Color(0x1A00E5FF)
                val borderColor = if (isSelected) ElectricCyan else Color(0x228CA7C2)
                val iconTint = if (isSelected) ElectricCyan else InputPlaceholder

                Box(
                    modifier = Modifier
                        .size(54.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(itemBg)
                        .border(
                            width = if (isSelected) 2.dp else 1.dp,
                            color = borderColor,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .clickable { onItemSelected(item) },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = item.iconRes),
                        contentDescription = item.contentDescription,
                        tint = iconTint,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF030D1A)
@Composable
fun BottomNavBarPreview() {
    DGWalkerTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp)
        ) {
            BottomNavBar(
                selectedItem = BottomNavItem.HOME,
                onItemSelected = {}
            )
        }
    }
}
