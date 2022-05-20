package com.example.pokemonapp.model.pokemon


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GenerationI(
    @SerializedName("red-blue")
    val redBlue: RedBlue,
    val yellow: Yellow
): Serializable