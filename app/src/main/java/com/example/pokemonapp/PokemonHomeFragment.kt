package com.example.pokemonapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation.findNavController
import com.example.pokemonapp.databinding.FragmentPokemonHomeBinding
import com.example.pokemonapp.ui.MainViewModel
import com.example.pokemonapp.ui.adapter.RecyclerViewAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.sharedViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [PokemonHomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PokemonHomeFragment : Fragment() {

    private val viewModel by sharedViewModel<MainViewModel>()
    private lateinit var binding: FragmentPokemonHomeBinding
    private lateinit var pokemonAdapter: RecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokemonHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViews()
    }

    fun setViews() {
        initRecyclerView(binding.root)
        lifecycleScope.launch {
            viewModel.getPokemons().collectLatest {
                pokemonAdapter.submitData(it)
            }
        }
    }

    fun initRecyclerView(view: View){
        pokemonAdapter = RecyclerViewAdapter().apply {
            gotItItemClickListener = {
                viewModel.setPokemonSelected(it)
                findNavController(view).navigate(R.id.action_pokemonHomeFragment_to_pokemonDetailFragment)
            }
        }
        binding.recyclerPokemon.adapter = pokemonAdapter
    }

}