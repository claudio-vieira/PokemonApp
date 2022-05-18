package com.example.pokemonapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ConcatAdapter
import com.example.pokemonapp.data.model.response.pokemon.Pokemon
import com.example.pokemonapp.databinding.ActivityMainBinding
import com.example.pokemonapp.ui.adapter.RecyclerViewAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding
    private lateinit var pokemonAdapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        viewModel.pokemonLiveData.observe(this) { pokemons ->
            pokemonAdapter.submitList(pokemons)
        }

        viewModel.getPokemons()

        initRecyclerView()
    }

    fun initRecyclerView(){
        pokemonAdapter = RecyclerViewAdapter().apply {
            gotItItemClickListener = {
                seeDetails(it)
            }
        }
        binding.recyclerPokemon.adapter = ConcatAdapter(pokemonAdapter)
    }

    private fun seeDetails(pokemon: Pokemon) {

        val intent = Intent(this, PokemonDetailActivity::class.java)
        intent.putExtra("pokemon", pokemon)
        startActivity(intent)
    }

}