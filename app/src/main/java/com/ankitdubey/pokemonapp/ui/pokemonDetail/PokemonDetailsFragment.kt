package com.ankitdubey.pokemonapp.ui.pokemonDetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.ankitdubey.pokemonapp.R
import com.ankitdubey.pokemonapp.databinding.FragmentPokemonDetailBinding
import com.ankitdubey.pokemonapp.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Ankit Dubey on 13,November,2021
 */

@AndroidEntryPoint
class PokemonDetailsFragment :
    BaseFragment<FragmentPokemonDetailBinding>(R.layout.fragment_pokemon_detail) {

    private val args by navArgs<PokemonDetailsFragmentArgs>()
    private val viewModel by viewModels<PokemonDetailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeStates()
        fetchPokemon()
    }

    private fun observeStates() {
        viewModel.pokemonLiveData.observe(viewLifecycleOwner){
            binding.pokemon = it
        }
    }

    private fun fetchPokemon() {
        viewModel.fetchPokemonDetail(args.pokemonId)
    }
}