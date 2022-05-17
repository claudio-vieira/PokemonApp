package com.example.pokemonapp.data.model.response.pokemon


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GenerationV(
    @SerializedName("black-white")
    val blackWhite: BlackWhite
): Serializable