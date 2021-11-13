package com.ankitdubey.pokemonapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import com.ankitdubey.pokemonapp.models.Pokemon

/**
 * Created by Ankit Dubey on 13,November,2021
 */


@Dao
interface PokemonDao {
    @Query("SELECT * FROM pokemon")
    fun getAll(): LiveData<List<PokemonEntity>>

    @Insert(onConflict = REPLACE)
    fun insertAll(users: List<PokemonEntity>)


    @Query("DELETE from pokemon")
    fun deleteAll()

    @Transaction
    fun updatePokemon(pokemonList: List<PokemonEntity>) {
        deleteAll()
        insertAll(pokemonList)
    }
}