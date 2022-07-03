package com.example.pokemonapp

import android.net.Uri
import androidx.paging.PagingSource
import com.example.pokemonapp.api.PokemonRepository
import com.example.pokemonapp.model.pokemon.Pokemon

class PokemonPagingDataSource(private val repository: PokemonRepository) :
    PagingSource<Int, Pokemon>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        val pageNumber = params.key ?: 0
        return try {
            val response = repository.getPokemonsListResponse(pageNumber)

            val pokemonList = mutableListOf<Pokemon>()

            response.results.forEach {
                val pokemon = repository.myPokemonByName(it.name)
                pokemonList.add(pokemon)
            }

            var nextPageNumber: Int? = null
            response.next?.let {
                val uri = Uri.parse(response.next)
                val nextPageQuery = uri.getQueryParameter("offset")
                nextPageNumber = nextPageQuery?.toInt()
            }

            LoadResult.Page(
                data = pokemonList,
                prevKey = null,
                nextKey = nextPageNumber
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}