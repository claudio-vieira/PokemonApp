package com.example.pokemonapp.data.model.response.pokemon


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RedBlue(
    @SerializedName("back_default")
    val backDefault: String,
    @SerializedName("back_gray")
    val backGray: String,
    @SerializedName("front_default")
    val frontDefault: String,
    @SerializedName("front_gray")
    val frontGray: String
): Serializable