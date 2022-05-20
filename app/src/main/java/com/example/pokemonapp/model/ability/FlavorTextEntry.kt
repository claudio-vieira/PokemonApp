package com.example.pokemonapp.model.ability


import com.google.gson.annotations.SerializedName

data class FlavorTextEntry(
    @SerializedName("flavor_text")
    val flavorText: String,
    val language: LanguageX,
    @SerializedName("version_group")
    val versionGroup: VersionGroup
)