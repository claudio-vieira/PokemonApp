package com.example.pokemonapp.data.model.response.pokemon


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MoveLearnMethod(
    val name: String,
    val url: String
): Serializable