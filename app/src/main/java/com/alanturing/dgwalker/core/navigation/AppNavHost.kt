package com.alanturing.dgwalker.core.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.alanturing.dgwalker.core.ui.components.BottomNavBar
import com.alanturing.dgwalker.core.ui.components.BottomNavItem
import com.alanturing.dgwalker.features.auth.ui.ForgotPasswordScreen
import com.alanturing.dgwalker.features.auth.ui.LoginScreen
import com.alanturing.dgwalker.features.auth.ui.RegisterScreen
import com.alanturing.dgwalker.features.digimon.ui.DigimonScreen
import com.alanturing.dgwalker.features.exchange.ui.ExchangeConfirmationScreen
import com.alanturing.dgwalker.features.exchange.ui.ExchangeConnectionScreen
import com.alanturing.dgwalker.features.exchange.ui.ExchangePrepareScreen
import com.alanturing.dgwalker.features.exchange.ui.ExchangeQrScreen
import com.alanturing.dgwalker.features.exchange.ui.ExchangeResultScreen
import com.alanturing.dgwalker.features.friends.ui.FriendAddScreen
import com.alanturing.dgwalker.features.friends.ui.FriendListScreen
import com.alanturing.dgwalker.features.home.ui.HomeScreen
import com.alanturing.dgwalker.features.items.ui.ItemsScreen
import com.alanturing.dgwalker.features.profile.ui.ProfileScreen
import com.alanturing.dgwalker.features.settings.ui.SettingsScreen

@Composable
fun AppNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val showBottomBar = currentRoute in listOf(
        Routes.Home.route,
        Routes.Digimon.route,
        Routes.Items.route,
        Routes.ExchangePrepare.route,
        Routes.Profile.route,
        Routes.FriendList.route,
        Routes.FriendAdd.route,
        Routes.Settings.route
    )

    val selectedNavItem = when (currentRoute) {
        Routes.Home.route -> BottomNavItem.HOME
        Routes.Digimon.route -> BottomNavItem.DIGIMON
        Routes.Items.route -> BottomNavItem.ITEMS
        Routes.ExchangePrepare.route -> BottomNavItem.EXCHANGE
        else -> BottomNavItem.HOME
    }

    Scaffold(
        modifier = modifier,
        bottomBar = {
            if (showBottomBar) {
                BottomNavBar(
                    selectedItem = selectedNavItem,
                    onItemSelected = { item ->
                        if (currentRoute != item.route) {
                            navController.navigate(item.route) {
                                popUpTo(Routes.Home.route) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.Login.route,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(Routes.Login.route) {
                LoginScreen(
                    onNavigateToHome = { navController.navigate(Routes.Home.route) },
                    onNavigateToRegister = { navController.navigate(Routes.Register.route) },
                    onNavigateToForgotPassword = { navController.navigate(Routes.ForgotPassword.route) }
                )
            }

            composable(Routes.Register.route) {
                RegisterScreen(
                    onNavigateToHome = { navController.navigate(Routes.Home.route) },
                    onNagivateToLogin = { navController.navigate(Routes.Login.route) }
                )
            }

            composable(Routes.ForgotPassword.route) {
                ForgotPasswordScreen(
                    onNavigateToLogin = { navController.navigate(Routes.Login.route) }
                )
            }

            composable(Routes.Home.route) {
                HomeScreen(
                    onNavigateToProfile = { navController.navigate(Routes.Profile.route) },
                    onNavigateToDigimon = { navController.navigate(Routes.Digimon.route) },
                    onNavigateToItems = { navController.navigate(Routes.Items.route) },
                    onNavigateToExchange = { navController.navigate(Routes.ExchangePrepare.route) }
                )
            }

            composable(Routes.Profile.route) {
                ProfileScreen(
                    onNavigateToHome = { navController.navigate(Routes.Home.route) },
                    onNavigateToDigimon = { navController.navigate(Routes.Digimon.route) },
                    onNavigateToItems = { navController.navigate(Routes.Items.route) },
                    onNavigateToExchange = { navController.navigate(Routes.ExchangePrepare.route) },
                    onNavigateToFriendList = { navController.navigate(Routes.FriendList.route) },
                    onNavigateToSettings = { navController.navigate(Routes.Settings.route) }
                )
            }

            composable(Routes.FriendList.route) {
                FriendListScreen(
                    onNavigateToHome = { navController.navigate(Routes.Home.route) },
                    onNavigateToDigimon = { navController.navigate(Routes.Digimon.route) },
                    onNavigateToItems = { navController.navigate(Routes.Items.route) },
                    onNavigateToExchange = { navController.navigate(Routes.ExchangePrepare.route) },
                    onNavigateToFriendAdd = { navController.navigate(Routes.FriendAdd.route) }
                )
            }

            composable(Routes.FriendAdd.route) {
                FriendAddScreen(
                    onNavigateToHome = { navController.navigate(Routes.Home.route) },
                    onNavigateToDigimon = { navController.navigate(Routes.Digimon.route) },
                    onNavigateToItems = { navController.navigate(Routes.Items.route) },
                    onNavigateToExchange = { navController.navigate(Routes.ExchangePrepare.route) },
                    onNavigateToFriendList = { navController.navigate(Routes.FriendList.route) }
                )
            }

            composable(Routes.Digimon.route) {
                DigimonScreen(
                    onNavigateToHome = { navController.navigate(Routes.Home.route) },
                    onNavigateToItems = { navController.navigate(Routes.Items.route) },
                    onNavigateToExchange = { navController.navigate(Routes.ExchangePrepare.route) }
                )
            }

            composable(Routes.Items.route) {
                ItemsScreen(
                    onNavigateToHome = { navController.navigate(Routes.Home.route) },
                    onNavigateToDigimon = { navController.navigate(Routes.Digimon.route) },
                    onNavigateToExchange = { navController.navigate(Routes.ExchangePrepare.route) }
                )
            }

            composable(Routes.Settings.route) {
                SettingsScreen(
                    onNavigateToProfile = { navController.navigate(Routes.Profile.route) }
                )
            }

            composable(Routes.ExchangePrepare.route) {
                ExchangePrepareScreen(
                    onNavigateToHome = { navController.navigate(Routes.Home.route) },
                    onNavigateToConnection = { navController.navigate(Routes.ExchangeConnection.route) }
                )
            }

            composable(Routes.ExchangeConnection.route) {
                ExchangeConnectionScreen(
                    onNavigateToPrepare = { navController.navigate(Routes.ExchangePrepare.route) },
                    onNavigateToConfirmation = { navController.navigate(Routes.ExchangeConfirmation.route) },
                    onNavigateToQr = { navController.navigate(Routes.ExchangeQr.route) }
                )
            }

            composable(Routes.ExchangeConfirmation.route) {
                ExchangeConfirmationScreen(
                    onNavigateToPrepare = { navController.navigate(Routes.ExchangePrepare.route) },
                    onNavigateToResult = { navController.navigate(Routes.ExchangeResult.route) }
                )
            }

            composable(Routes.ExchangeQr.route) {
                ExchangeQrScreen(
                    onNavigateToConnection = { navController.navigate(Routes.ExchangeConnection.route) },
                    onNavigateToResult = { navController.navigate(Routes.ExchangeResult.route) }
                )
            }

            composable(Routes.ExchangeResult.route) {
                ExchangeResultScreen(
                    onNavigateToHome = { navController.navigate(Routes.Home.route) },
                    onNavigateToDigimon = { navController.navigate(Routes.Digimon.route) }
                )
            }
        }
    }
}
