package com.example.pokemonapp.model.pokemon


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Ability(
    val ability: AbilityX,
    @SerializedName("is_hidden")
    val isHidden: Boolean,
    val slot: Int
): Serializable