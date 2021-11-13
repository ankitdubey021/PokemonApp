package com.ankitdubey.pokemonapp.ui.pokemonList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ankitdubey.pokemonapp.databinding.FragmentPokemonListBinding
import com.ankitdubey.pokemonapp.extensions.addDivider
import dagger.hilt.android.AndroidEntryPoint

import com.ankitdubey.pokemonapp.extensions.onScrolledToBottom
import com.ankitdubey.pokemonapp.utils.ProgressBarUtils


/**
 * Created by Ankit Dubey on 13,November,2021
 */

@AndroidEntryPoint
class PokemonListFragment : Fragment() {

    private var _binding: FragmentPokemonListBinding? = null
    private val binding get() = _binding!!

    private val pokemonViewModel by viewModels<PokemonListViewModel>()
    private lateinit var pokemonListAdapter: PokemonListAdapter
    private var offset : Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
        fetchPokemons()
        observeStates()
    }

    private fun initViews() {
        pokemonListAdapter = PokemonListAdapter()

        binding.pokemonListRv.apply {
            this.adapter = pokemonListAdapter
            addDivider()
            onScrolledToBottom {
                fetchPokemons()
            }
        }
    }

    private fun observeStates() {
        pokemonViewModel.pokemons.observe(viewLifecycleOwner){
            offset = it.size
            pokemonListAdapter.pokemonItems = it
        }

        pokemonViewModel.loadingLiveData.observe(viewLifecycleOwner){
            if(it)
                ProgressBarUtils.showProgressDialog(requireContext())
            else
                ProgressBarUtils.removeProgressDialog()
        }
    }

    private fun fetchPokemons() {
        pokemonViewModel.fetchPokemon(offset)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}