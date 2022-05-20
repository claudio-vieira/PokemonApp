package com.example.pokemonapp.api

import com.example.pokemonapp.model.PokemonListResponse
import com.example.pokemonapp.model.ability.AbilityDetail

import com.example.pokemonapp.model.pokemon.Pokemon
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {

    @GET("pokemon")
    suspend fun myPokemons(@Query("limit") limit: Int, @Query("offset") offset: Int) : PokemonListResponse

    @GET("{url}")
    suspend fun myPokemonsPreviousPage(@Path("url") url: String) : Response<List<Pokemon>>

    @GET("{url}")
    suspend fun myPokemonsNextPage(@Path("url") url: String) : Response<List<Pokemon>>

    @GET("pokemon/{id}/")
    suspend fun myPokemonById(@Path("id") id: Int) : Response<Pokemon>

    @GET("pokemon/{name}")
    suspend fun myPokemonByName(@Path("name") name: String) : Pokemon

    @GET("ability/{name}")
    suspend fun getAbilityByName(@Path("name") name: String) : AbilityDetail
}