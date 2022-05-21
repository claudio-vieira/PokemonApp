package com.example.pokemonapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.databinding.ItemActivityDetailStatsBinding
import com.example.pokemonapp.model.pokemon.Stat

class RecyclerViewDetailStatsAdapter:
    ListAdapter<Stat, RecyclerViewDetailStatsAdapter.ViewHolder>(DIFF_CALLBALCK) {

    companion object {
        private val DIFF_CALLBALCK = object: DiffUtil.ItemCallback<Stat>(){
            override fun areContentsTheSame(oldItem: Stat, newItem: Stat): Boolean {
                return oldItem.stat.name == newItem.stat.name
            }

            override fun areItemsTheSame(oldItem: Stat, newItem: Stat): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ItemActivityDetailStatsBinding) : RecyclerView.ViewHolder(binding.root) {

        private val LANG = "en"

        fun bind(stat: Stat){
            binding.run {
                tvDetailStats.text = stat.stat.name
                pbDetailStats.progress = stat.baseStat
            }
        }

        companion object {
            fun create(parent: ViewGroup) : ViewHolder {

                val itemBinding = ItemActivityDetailStatsBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false)

                return ViewHolder(itemBinding)
            }
        }
    }
}