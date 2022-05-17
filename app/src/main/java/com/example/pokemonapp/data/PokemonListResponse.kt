package com.example.pokemonapp.data


import com.google.gson.annotations.SerializedName

data class PokemonListResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)