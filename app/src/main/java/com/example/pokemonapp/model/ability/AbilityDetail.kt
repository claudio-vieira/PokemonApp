package com.example.pokemonapp.model.ability


import com.google.gson.annotations.SerializedName

data class AbilityDetail(
    @SerializedName("effect_changes")
    val effectChanges: List<Any>,
    @SerializedName("effect_entries")
    val effectEntries: MutableList<EffectEntry>,
    @SerializedName("flavor_text_entries")
    val flavorTextEntries: List<FlavorTextEntry>,
    val generation: Generation,
    val id: Int,
    @SerializedName("is_main_series")
    val isMainSeries: Boolean,
    val name: String,
    val names: List<Name>,
    val pokemon: List<Pokemon>
)