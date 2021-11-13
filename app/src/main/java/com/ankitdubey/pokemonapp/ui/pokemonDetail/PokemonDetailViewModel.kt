package com.ankitdubey.pokemonapp.ui.pokemonDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ankitdubey.pokemonapp.database.PokemonEntity
import com.ankitdubey.pokemonapp.database.toDBPokemon
import com.ankitdubey.pokemonapp.network.PokemonService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Ankit Dubey on 13,November,2021
 */

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val pokemonService: PokemonService,
): ViewModel(){

    private val _pokemonLiveData = MutableLiveData<PokemonEntity>()
    val pokemonLiveData: LiveData<PokemonEntity> = _pokemonLiveData

    fun fetchPokemonDetail(pokemonId : String){
        viewModelScope.launch {
            val pokemon = pokemonService.getPokemonById(pokemonId)
            _pokemonLiveData.postValue(pokemon.toDBPokemon())
        }
    }
}