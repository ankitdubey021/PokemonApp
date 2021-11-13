package com.ankitdubey.pokemonapp.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Ankit Dubey on 13,November,2021
 */

data class Pokemon(
    val name: String,
    var height: Int? = null,
    var weight: Int? = null,

    @SerializedName("url")
    val detailURL: String,
    val sprites: Other? = null,
)

data class PokemonList(
    val results: List<Pokemon>,
    val count: Long
)

data class Other(
    val other: HomeImage
)

data class HomeImage(
    val home: PokemonImage
)

data class PokemonImage(
    @SerializedName("front_default")
    val frontDefault: String? = null
)
