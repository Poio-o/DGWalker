package com.alanturing.dgwalker.features.data.model

data class User(
    val id: String,
    val username: String,
    val email: String,
    val createdAt: String? = null
) {
    val uid: String get() = id
}
