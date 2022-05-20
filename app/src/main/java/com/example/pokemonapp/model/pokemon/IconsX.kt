package com.example.pokemonapp.model.pokemon


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class IconsX(
    @SerializedName("front_default")
    val frontDefault: String,
    @SerializedName("front_female")
    val frontFemale: Any
): Serializable