package com.example.pokemonapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.example.pokemonapp.databinding.FragmentPokemonHomeBinding
import com.example.pokemonapp.ui.MainViewModel
import com.example.pokemonapp.ui.adapter.RecyclerViewAdapter
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

        viewModel.pokemonLiveData.observe(viewLifecycleOwner) { pokemons ->
            pokemonAdapter.submitList(pokemons)
        }

        viewModel.getPokemons()
        initRecyclerView(binding.root)

        return binding.root
    }

    fun initRecyclerView(view: View){
        pokemonAdapter = RecyclerViewAdapter().apply {
            gotItItemClickListener = {
                findNavController(view).navigate(R.id.action_pokemonHomeFragment_to_pokemonDetailFragment)
            }
        }
        binding.recyclerPokemon.adapter = ConcatAdapter(pokemonAdapter)
    }

}