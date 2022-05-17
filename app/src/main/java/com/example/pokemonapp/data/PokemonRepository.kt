package com.example.pokemonapp.data

import com.example.pokemonapp.data.api.PokemonApi
import com.example.pokemonapp.data.model.response.ability.AbilityDetail

import com.example.pokemonapp.data.model.response.pokemon.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonRepository(private val service: PokemonApi) {

    suspend fun getPokemonsListResponse(): PokemonListResponse {
        return withContext(Dispatchers.Default){
            service.myPokemons(10, 0)
        }
    }

    suspend fun myPokemonByName(name: String): Pokemon {
        return withContext(Dispatchers.Default){
            service.myPokemonByName(name)
        }
    }

    suspend fun getAbilityByName(name: String): AbilityDetail {
        return withContext(Dispatchers.Default){
            service.getAbilityByName(name)
        }
    }
}