package com.example.pokemonapp.data.model.response.pokemon


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GenerationVi(
    @SerializedName("omegaruby-alphasapphire")
    val omegarubyAlphasapphire: OmegarubyAlphasapphire,
    @SerializedName("x-y")
    val xY: XY
): Serializable