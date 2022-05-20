package com.example.pokemonapp.model.pokemon


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Stat(
    @SerializedName("base_stat")
    val baseStat: Int,
    val effort: Int,
    val stat: StatX
): Serializable