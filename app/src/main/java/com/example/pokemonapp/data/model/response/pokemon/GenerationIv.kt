package com.example.pokemonapp.data.model.response.pokemon


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GenerationIv(
    @SerializedName("diamond-pearl")
    val diamondPearl: DiamondPearl,
    @SerializedName("heartgold-soulsilver")
    val heartgoldSoulsilver: HeartgoldSoulsilver,
    val platinum: Platinum
): Serializable