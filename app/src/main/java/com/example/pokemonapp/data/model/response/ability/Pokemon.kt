package com.example.pokemonapp.data.model.response.ability


import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("is_hidden")
    val isHidden: Boolean,
    val pokemon: PokemonX,
    val slot: Int
)