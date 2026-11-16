package com.alanturing.dgwalker.core.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes(val route: String) {

    @Serializable
    object Home : Routes("HomeScreen")
    @Serializable
    object Settings : Routes("SettingsScreen")
    @Serializable
    object Digimon : Routes("DigimonScreen")
    @Serializable
    object Items : Routes("ItemsScreen")
    @Serializable
    object Profile : Routes("ProfileScreen")
    @Serializable
    object Login : Routes("LoginScreen")
    @Serializable
    object Register : Routes("RegisterScreen")
    @Serializable
    object ForgotPassword : Routes("ForgotPasswordScreen")
    @Serializable
    object FriendList : Routes("FriendListScreen")
    @Serializable
    object FriendAdd : Routes( "FriendAddScreen")
    @Serializable
    object ExchangePrepare : Routes("ExchangeScreen")
    @Serializable
    object ExchangeConnection : Routes("ExchangeConnectionScreen")
    @Serializable
    object ExchangeConfirmation : Routes("ExchangeConfirmationScreen")
    @Serializable
    object ExchangeResult : Routes("ExchangeResultScreen")
    @Serializable
    object ExchangeQr : Routes("ExchangeQrScreen")
    @Serializable
    class ChatDetail(chatUID:String) : Routes("ChatDetailScreen/${chatUID}")
    @Serializable
    class FriendDetail(friendUID:String) : Routes("FriendDetailScreen/${friendUID}")
    @Serializable
    class DigimonDetail(digimonUID:String) : Routes("DigimonDetailScreen/${digimonUID}")
}