package com.ankitdubey.pokemonapp.ui.pokemonList

/**
 * Created by Ankit Dubey on 13,November,2021
 */

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ankitdubey.pokemonapp.database.PokemonEntity
import com.ankitdubey.pokemonapp.databinding.ListPokemonBinding

class PokemonListAdapter(val callback: (String) -> Unit) :
    RecyclerView.Adapter<PokemonListAdapter.MyViewHolder>() {
    var pokemonItems: List<PokemonEntity> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val withDataBinding = ListPokemonBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(withDataBinding)
    }

    override fun getItemCount() = pokemonItems.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val pokemon = pokemonItems[position]
        holder.viewDataBinding.apply {
            pokemonNameTv.text = pokemon.name
            root.setOnClickListener {
                callback(pokemon.id)
            }
        }
    }

    class MyViewHolder(val viewDataBinding: ListPokemonBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root)
}