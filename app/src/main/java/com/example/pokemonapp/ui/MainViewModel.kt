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

    private val _pokemonDetailLiveData = MutableLiveData<Pokemon>()
    val pokemonDetailLiveData: LiveData<Pokemon> = _pokemonDetailLiveData

    private val _abilityDetailLiveData = MutableLiveData<List<AbilityDetail>>()
    val abilityDetailLiveData: LiveData<List<AbilityDetail>> = _abilityDetailLiveData

    private val _pokemonSelectedLiveData = MutableLiveData<Pokemon>()
    val pokemonSelectedLiveData: LiveData<Pokemon> = _pokemonSelectedLiveData

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
        _pokemonSelectedLiveData.postValue(pokemon)
    }

}