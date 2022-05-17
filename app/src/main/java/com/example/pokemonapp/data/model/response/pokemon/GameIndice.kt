package com.example.pokemonapp.data.model.response.pokemon


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GameIndice(
    @SerializedName("game_index")
    val gameIndex: Int,
    val version: Version
): Serializable