package com.example.pokemonapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemonapp.api.PokemonRepository
import com.example.pokemonapp.model.pokemon.Pokemon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: PokemonRepository): ViewModel() {

    private val _pokemonLiveData = MutableLiveData<List<Pokemon>>()
    val pokemonLiveData: LiveData<List<Pokemon>> = _pokemonLiveData

    fun getPokemons(){
        CoroutineScope(Dispatchers.Main).launch {

            val pokemonList = mutableListOf<Pokemon>()

            /*val pokemons = withContext(Dispatchers.Default) {
                repository.getPokemonsListResponse()
            }*/
            val pokemons = repository.getPokemonsListResponse()

            for(item in pokemons.results){
                /*val pokemon = withContext(Dispatchers.Default) {
                    repository.myPokemonByName(item.name)
                }*/
                val pokemon = repository.myPokemonByName(item.name)
                pokemonList.add(pokemon)
            }

            _pokemonLiveData.value = pokemonList
        }
    }

}