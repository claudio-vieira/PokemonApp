package com.example.pokemonapp.model.ability


import com.google.gson.annotations.SerializedName

data class EffectEntry(
    val effect: String,
    val language: Language,
    @SerializedName("short_effect")
    val shortEffect: String
)