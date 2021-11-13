package com.ankitdubey.pokemonapp.ui.pokemonList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ankitdubey.pokemonapp.database.PokemonDao
import com.ankitdubey.pokemonapp.database.toPokemonDBList
import com.ankitdubey.pokemonapp.network.PokemonService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Ankit Dubey on 13,November,2021
 */

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val pokemonService: PokemonService,
    private val pokemonDao: PokemonDao
) : ViewModel() {

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> = _loadingLiveData

    val pokemons = pokemonDao.getAll()

    fun fetchPokemon(offset: Int) {
        viewModelScope.launch(Dispatchers.IO) {

            if (_loadingLiveData.value == true)
                return@launch

            _loadingLiveData.postValue(true)
            val pokemon = pokemonService.fetchPokemon(offset, 30)

            if (offset == 0)
                pokemonDao.updatePokemon(pokemon.results.toPokemonDBList())
            else
                pokemonDao.insertAll(pokemon.results.toPokemonDBList())

            _loadingLiveData.postValue(false)
        }
    }

}
