package com.example.pokemonapp.data.model.response.pokemon


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class VersionGroup(
    val name: String,
    val url: String
): Serializable