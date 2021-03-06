package com.example.pokemonapp

import com.example.pokemonapp.api.PokemonRepository
import com.example.pokemonapp.api.PokemonApi
import com.example.pokemonapp.ui.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val URL_BASE = "https://pokeapi.co/api/v2/"

val retrofitModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single<PokemonApi> { get<Retrofit>().create(PokemonApi::class.java) }
}

val repositoryModule = module {
    single { PokemonRepository(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}

val appModules = listOf(
    retrofitModule, repositoryModule, viewModelModule
)