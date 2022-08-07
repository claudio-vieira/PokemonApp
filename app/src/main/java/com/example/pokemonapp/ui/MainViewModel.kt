package com.example.pokemonapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.pokemonapp.PokemonPagingDataSource
import com.example.pokemonapp.api.PokemonRepository
import com.example.pokemonapp.model.ability.AbilityDetail
import com.example.pokemonapp.model.pokemon.Ability
import com.example.pokemonapp.model.pokemon.Pokemon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: PokemonRepository): ViewModel() {

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

    fun getPokemons(): Flow<PagingData<Pokemon>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 2
            ),
            pagingSourceFactory = { PokemonPagingDataSource(repository) }
        ).flow.cachedIn(viewModelScope)
    }

    fun setPokemonSelected(pokemon: Pokemon){
        _pokemonSelectedLiveData.value = pokemon
    }

}