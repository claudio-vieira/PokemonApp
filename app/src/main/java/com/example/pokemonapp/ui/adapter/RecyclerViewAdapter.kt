package com.example.pokemonapp.ui.adapter

import android.R
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.model.pokemon.Pokemon
import com.example.pokemonapp.databinding.ItemPokemonBinding
import com.squareup.picasso.Picasso

class RecyclerViewAdapter :
    PagingDataAdapter<Pokemon, RecyclerViewAdapter.ViewHolder>(DIFF_CALLBALCK){

    var gotItItemClickListener: ((Pokemon) -> Unit)? = null

    companion object {
        private val DIFF_CALLBALCK = object : DiffUtil.ItemCallback<Pokemon>() {
            override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.create(parent, gotItItemClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class ViewHolder(
        private val binding: ItemPokemonBinding,
        private val gotItItemClickListener: ((Pokemon) -> Unit)?
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(pokemon: Pokemon) {

            binding.run {

                textPokemonName.text = pokemon.name
                Picasso
                    .get()
                    .load(pokemon.sprites.other.officialArtwork.frontDefault)
                    .error(R.drawable.ic_dialog_alert)
                    .into(imagePokemon)
            }

            binding.layoutItemPokemon.setOnClickListener {
                gotItItemClickListener?.invoke(pokemon)
            }

        }

        companion object {
            fun create(
                parent: ViewGroup,
                gotItItemClickListener: ((Pokemon) -> Unit)?
            ) : ViewHolder{
                val itemBinding = ItemPokemonBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false)

                return ViewHolder(itemBinding, gotItItemClickListener)
            }
        }
    }
}