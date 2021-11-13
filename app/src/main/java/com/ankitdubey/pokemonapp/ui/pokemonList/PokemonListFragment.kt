package com.ankitdubey.pokemonapp.ui.pokemonList

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ankitdubey.pokemonapp.R
import com.ankitdubey.pokemonapp.databinding.FragmentPokemonListBinding
import com.ankitdubey.pokemonapp.extensions.addDivider
import com.ankitdubey.pokemonapp.extensions.onScrolledToBottom
import com.ankitdubey.pokemonapp.ui.BaseFragment
import com.ankitdubey.pokemonapp.utils.ProgressBarUtils
import dagger.hilt.android.AndroidEntryPoint


/**
 * Created by Ankit Dubey on 13,November,2021
 */

@AndroidEntryPoint
class PokemonListFragment :
    BaseFragment<FragmentPokemonListBinding>(R.layout.fragment_pokemon_list) {

    private val pokemonViewModel by viewModels<PokemonListViewModel>()
    private lateinit var pokemonListAdapter: PokemonListAdapter
    private var offset: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchPokemons()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
        observeStates()
    }

    private fun initViews() {
        pokemonListAdapter = PokemonListAdapter(callback = { id ->
            findNavController().navigate(
                PokemonListFragmentDirections.showPokemon(id)
            )
        })

        binding.pokemonListRv.apply {
            this.adapter = pokemonListAdapter
            addDivider()
            onScrolledToBottom {
                fetchPokemons()
            }
        }
    }

    private fun observeStates() {
        pokemonViewModel.pokemons.observe(viewLifecycleOwner) {
            offset = it.size
            pokemonListAdapter.pokemonItems = it
        }

        pokemonViewModel.loadingLiveData.observe(viewLifecycleOwner) {
            if (it)
                ProgressBarUtils.showProgressDialog(requireContext())
            else
                ProgressBarUtils.removeProgressDialog()
        }
    }

    private fun fetchPokemons() {
        pokemonViewModel.fetchPokemon(offset)
    }
}