package com.example.pokemonapp.ui

import android.R
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemonapp.data.PokemonRepository
import com.example.pokemonapp.data.model.response.ability.AbilityDetail
import com.example.pokemonapp.data.model.response.ability.EffectEntry
import com.example.pokemonapp.data.model.response.pokemon.Ability
import com.example.pokemonapp.data.model.response.pokemon.Pokemon
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class PokemonDetailViewModel(private val repository: PokemonRepository): ViewModel() {

    private val _pokemonDetailLiveData = MutableLiveData<Pokemon>()
    val pokemonDetailLiveData: LiveData<Pokemon>
        get() = _pokemonDetailLiveData

    private val _abilityDetailLiveData = MutableLiveData<List<AbilityDetail>>()
    val abilityDetailLiveData: LiveData<List<AbilityDetail>>
        get() = _abilityDetailLiveData

    fun getPokemonByName(name: String){
        CoroutineScope(Dispatchers.Main).launch {

            val pokemon = repository.myPokemonByName(name)
            _pokemonDetailLiveData.value = pokemon
        }
    }

    fun getAbilities(abilities: List<Ability>){
        CoroutineScope(Dispatchers.Main).launch {

            val abilitiesList = mutableListOf<AbilityDetail>()

            for(ability in abilities){
                val ability = repository.getAbilityByName(ability.ability.name)
                abilitiesList.add(ability)
            }

            _abilityDetailLiveData.value = abilitiesList
        }
    }
}

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, imageUrl: String?) {
    Picasso.get()
        .load(imageUrl)
        .error(R.drawable.ic_dialog_alert)
        //.placeholder(R.drawable.placeholder)
        .into(view)
}