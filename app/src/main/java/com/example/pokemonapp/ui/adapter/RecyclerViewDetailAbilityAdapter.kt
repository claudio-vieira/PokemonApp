package com.example.pokemonapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.databinding.ItemActivityDetailAbilitiesBinding
import com.example.pokemonapp.model.ability.AbilityDetail

class RecyclerViewDetailAbilityAdapter:
    ListAdapter<AbilityDetail, RecyclerViewDetailAbilityAdapter.ViewHolder>(DIFF_CALLBALCK) {

    companion object {
        private val DIFF_CALLBALCK = object: DiffUtil.ItemCallback<AbilityDetail>(){
            override fun areContentsTheSame(oldItem: AbilityDetail, newItem: AbilityDetail): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areItemsTheSame(oldItem: AbilityDetail, newItem: AbilityDetail): Boolean {
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

    class ViewHolder(private val binding: ItemActivityDetailAbilitiesBinding) : RecyclerView.ViewHolder(binding.root) {

        private val LANG = "en"

        fun bind(ability: AbilityDetail){
            binding.run {

                for(effect in ability.effectEntries){
                    if(effect.language.name == LANG){
                        textAbilityDetail.text = effect.effect
                    }
                }
            }
        }

        companion object {
            fun create(parent: ViewGroup) : ViewHolder {

                val itemBinding = ItemActivityDetailAbilitiesBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false)

                return ViewHolder(itemBinding)
            }
        }
    }
}