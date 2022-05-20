package com.example.pokemonapp.model.pokemon


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UltraSunUltraMoon(
    @SerializedName("front_default")
    val frontDefault: String,
    @SerializedName("front_female")
    val frontFemale: Any,
    @SerializedName("front_shiny")
    val frontShiny: String,
    @SerializedName("front_shiny_female")
    val frontShinyFemale: Any
): Serializable