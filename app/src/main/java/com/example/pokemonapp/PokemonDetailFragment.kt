package com.example.pokemonapp

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pokemonapp.databinding.FragmentPokemonDetailBinding
import com.example.pokemonapp.ui.MainViewModel
import com.example.pokemonapp.ui.adapter.RecyclerViewDetailAbilityAdapter
import com.example.pokemonapp.ui.adapter.RecyclerViewDetailStatsAdapter
import com.squareup.picasso.Picasso
import org.koin.android.viewmodel.ext.android.sharedViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [PokemonDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PokemonDetailFragment : Fragment() {

    private val viewmodel by sharedViewModel<MainViewModel>()
    private lateinit var binding: FragmentPokemonDetailBinding
    private lateinit var abilityAdapter: RecyclerViewDetailAbilityAdapter
    private lateinit var statsAdapter: RecyclerViewDetailStatsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokemonDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        setViews()
    }

    fun setViews() {

        viewmodel.pokemonSelectedLiveData.value?.abilities?.let { viewmodel.getAbilities(it) }

        binding.apply {
            Picasso
                .get()
                .load(viewmodel.pokemonSelectedLiveData.value?.sprites?.other?.officialArtwork?.frontDefault)
                .error(R.drawable.ic_dialog_alert)
                .into(ivDetailPokemon)

            tvDetailPokemonName.text = viewmodel.pokemonSelectedLiveData.value?.name
        }

        statsAdapter = RecyclerViewDetailStatsAdapter()
        binding.recyclerDetailStats.adapter = statsAdapter
        statsAdapter.submitList(viewmodel.pokemonSelectedLiveData.value?.stats)

        abilityAdapter = RecyclerViewDetailAbilityAdapter()
        binding.recyclerDetailAbities.adapter = abilityAdapter
    }

    fun setObservers() {
        viewmodel.abilityDetailLiveData.observe(viewLifecycleOwner) { ability ->
            abilityAdapter.submitList(ability)
        }
    }

}