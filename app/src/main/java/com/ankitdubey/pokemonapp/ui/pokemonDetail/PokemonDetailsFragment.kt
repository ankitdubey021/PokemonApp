package com.ankitdubey.pokemonapp.ui.pokemonDetail

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.ankitdubey.pokemonapp.R
import com.ankitdubey.pokemonapp.databinding.FragmentPokemonDetailBinding
import com.ankitdubey.pokemonapp.ui.BaseFragment

/**
 * Created by Ankit Dubey on 13,November,2021
 */
class PokemonDetailsFragment :
    BaseFragment<FragmentPokemonDetailBinding>(R.layout.fragment_pokemon_detail) {

    private val args by navArgs<PokemonDetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.pokemon = args.pokemon
    }
}