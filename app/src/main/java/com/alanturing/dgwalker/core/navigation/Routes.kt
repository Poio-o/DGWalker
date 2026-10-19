package com.alanturing.dgwalker.core.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes(val route: String) {

    object Home : Routes("homeScreen")
    object Settings : Routes("settingsScreen")
    object Digimon : Routes("digimonScreen")
    object Items : Routes("itemsScreen")
    object Profile : Routes("profileScreen")
    object Login : Routes("loginScreen")
    object Register : Routes("registerScreen")
    object ForgotPassword : Routes("forgotPasswordScreen")
    object FriendList : Routes("friendListScreen")

    object FriendAdd : Routes( "friendAddScreen")

    object ExchangePrepare : Routes("exchangeScreen")
    object ExchangeConnection : Routes("exchangeConnectionScreen")
    object ExchangeConfirmation : Routes("exchangeConfirmationScreen")
    object ExchangeResult : Routes("exchangeResultScreen")
    object ExchangeQR : Routes("exchangeQrScreen")

    object ChatDetail : Routes("chatDetailScreen/{chatId}") {
        fun createRoute(chatId: Int) = "chatDetailScreen/$chatId"
    }

    object FriendDetail : Routes("friendDetailScreen/{friendId}") {
        fun createRoute(friendId: Int) = "friendDetailScreen/$friendId"
    }

    object DigimonDetail : Routes("digimonDetailScreen/{digimonId}") {
        fun createRoute(digimonId: Int) = "digimonDetailScreen/$digimonId"
    }
}