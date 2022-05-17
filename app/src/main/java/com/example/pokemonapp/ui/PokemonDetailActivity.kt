package com.example.pokemonapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ConcatAdapter
import com.example.pokemonapp.data.model.response.pokemon.Pokemon
import com.example.pokemonapp.databinding.ActivityPokemonDetailBinding
import com.example.pokemonapp.ui.adapter.RecyclerViewAbilityAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class PokemonDetailActivity : AppCompatActivity() {

    private val viewModel: PokemonDetailViewModel by viewModel()
    private lateinit var binding: ActivityPokemonDetailBinding
    private lateinit var abilityAdapter: RecyclerViewAbilityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        val pokemon = intent.getSerializableExtra("pokemon") as Pokemon

        viewModel.abilityDetailLiveData.observe(this, Observer { ability ->
            abilityAdapter.submitList(ability)
        })

        viewModel.getPokemonByName(pokemon.name)
        viewModel.getAbilities(pokemon.abilities)

        initRecyclerView()
    }

    fun initRecyclerView(){
        abilityAdapter = RecyclerViewAbilityAdapter()
        binding.recyclerDetailAbities.adapter = ConcatAdapter(abilityAdapter)
    }

}