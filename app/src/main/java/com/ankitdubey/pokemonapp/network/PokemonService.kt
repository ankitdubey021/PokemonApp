package com.ankitdubey.pokemonapp.network

import com.ankitdubey.pokemonapp.models.Pokemon
import com.ankitdubey.pokemonapp.models.PokemonList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * Created by Ankit Dubey on 13,November,2021
 */
interface PokemonService {

    @GET("pokemon")
    suspend fun fetchPokemon(
        @Query("offset") pageNum: Int,
        @Query("limit") limit: Int,
    ): PokemonList

    @GET("pokemon/{id}")
    suspend fun getPokemonById(
        @Path("id") id: String
    ): Pokemon

}