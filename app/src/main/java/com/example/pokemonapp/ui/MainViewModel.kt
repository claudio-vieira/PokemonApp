package com.example.pokemonapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemonapp.api.PokemonRepository
import com.example.pokemonapp.model.ability.AbilityDetail
import com.example.pokemonapp.model.pokemon.Ability
import com.example.pokemonapp.model.pokemon.Pokemon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: PokemonRepository): ViewModel() {

    private val _pokemonLiveData = MutableLiveData<List<Pokemon>>()
    val pokemonLiveData: LiveData<List<Pokemon>> = _pokemonLiveData

    private val _abilityDetailLiveData = MutableLiveData<List<AbilityDetail>>()
    val abilityDetailLiveData: LiveData<List<AbilityDetail>> = _abilityDetailLiveData

    private val _pokemonSelectedLiveData = MutableLiveData<Pokemon>()
    val pokemonSelectedLiveData: LiveData<Pokemon> = _pokemonSelectedLiveData

    fun getAbilities(abilities: List<Ability>){
        CoroutineScope(Dispatchers.Main).launch {

            val abilitiesList = mutableListOf<AbilityDetail>()
            abilities.forEach {
                val ability = repository.getAbilityByName(it.ability.name)
                abilitiesList.add(ability)
            }

            _abilityDetailLiveData.value = abilitiesList
        }
    }

    fun getPokemons(){
        CoroutineScope(Dispatchers.Main).launch {

            val pokemonList = mutableListOf<Pokemon>()
            val pokemons = repository.getPokemonsListResponse()

            for(item in pokemons.results){
                val pokemon = repository.myPokemonByName(item.name)
                pokemonList.add(pokemon)
            }

            _pokemonLiveData.value = pokemonList
        }
    }

    fun setPokemonSelected(pokemon: Pokemon){
        _pokemonSelectedLiveData.value = pokemon
    }

}