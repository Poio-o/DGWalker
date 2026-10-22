package com.alanturing.dgwalker.core.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes(val route: String) {

    @Serializable
    object Home : Routes("homeScreen")
    @Serializable
    object Settings : Routes("settingsScreen")
    @Serializable
    object Digimon : Routes("digimonScreen")
    @Serializable
    object Items : Routes("itemsScreen")
    @Serializable
    object Profile : Routes("profileScreen")
    @Serializable
    object Login : Routes("loginScreen")
    @Serializable
    object Register : Routes("registerScreen")
    @Serializable
    object ForgotPassword : Routes("forgotPasswordScreen")
    @Serializable
    object FriendList : Routes("friendListScreen")
    @Serializable
    object FriendAdd : Routes( "friendAddScreen")
    @Serializable
    object ExchangePrepare : Routes("exchangeScreen")
    @Serializable
    object ExchangeConnection : Routes("exchangeConnectionScreen")
    @Serializable
    object ExchangeConfirmation : Routes("exchangeConfirmationScreen")
    @Serializable
    object ExchangeResult : Routes("exchangeResultScreen")
    @Serializable
    object ExchangeQR : Routes("exchangeQrScreen")
    @Serializable
    class ChatDetail(chatUID:String) : Routes("chatDetailScreen/${chatUID}")
    @Serializable
    class FriendDetail(friendUID:String) : Routes("friendDetailScreen/${friendUID}")
    @Serializable
    class DigimonDetail(digimonUID:String) : Routes("digimonDetailScreen/${digimonUID}")
}