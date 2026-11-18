package com.alanturing.dgwalker.features.data.remote.dto

import com.google.gson.annotations.SerializedName

data class DigimonRemoteDto(
    @SerializedName("digimonId") val digimonId: String,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("level") val level: Int,
    @SerializedName("currentExp") val currentExp: Int,
    @SerializedName("friendship") val friendship: Int,
    @SerializedName("hunger") val hunger: Int,
    @SerializedName("fatigue") val fatigue: Int,
    @SerializedName("isWalking") val isWalking: Boolean,

    @SerializedName("baseHp") val baseHp: Int,
    @SerializedName("baseSp") val baseSp: Int,
    @SerializedName("baseAtk") val baseAtk: Int,
    @SerializedName("baseDef") val baseDef: Int,
    @SerializedName("baseInt") val baseInt: Int,
    @SerializedName("baseSpi") val baseSpi: Int,
    @SerializedName("baseSpd") val baseSpd: Int,

    @SerializedName("npHp") val npHp: Int,
    @SerializedName("npSp") val npSp: Int,
    @SerializedName("npAtk") val npAtk: Int,
    @SerializedName("npDef") val npDef: Int,
    @SerializedName("npInt") val npInt: Int,
    @SerializedName("npSpi") val npSpi: Int,
    @SerializedName("npSpd") val npSpd: Int,

    @SerializedName("bonusHp") val bonusHp: Int = 0,
    @SerializedName("bonusSp") val bonusSp: Int = 0,
    @SerializedName("bonusAtk") val bonusAtk: Int = 0,
    @SerializedName("bonusDef") val bonusDef: Int = 0,
    @SerializedName("bonusInt") val bonusInt: Int = 0,
    @SerializedName("bonusSpi") val bonusSpi: Int = 0,
    @SerializedName("bonusSpd") val bonusSpd: Int = 0,

    @SerializedName("species") val species: SpeciesRemoteDto? = null
)
data class SpeciesRemoteDto(
    @SerializedName("name") val name: String,
    @SerializedName("generation") val generation: String,
    @SerializedName("attribute") val attribute: String,
    @SerializedName("type") val type: String,
    @SerializedName("spriteUrl") val spriteUrl: String
)