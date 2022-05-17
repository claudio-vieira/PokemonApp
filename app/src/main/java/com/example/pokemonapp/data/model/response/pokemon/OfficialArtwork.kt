package com.example.pokemonapp.data.model.response.pokemon


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class OfficialArtwork(
    @SerializedName("front_default")
    val frontDefault: String
): Serializable