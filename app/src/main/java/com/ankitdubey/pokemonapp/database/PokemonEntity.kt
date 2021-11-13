package com.ankitdubey.pokemonapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ankitdubey.pokemonapp.models.Pokemon
import java.io.Serializable

/**
 * Created by Ankit Dubey on 13,November,2021
 */

@Entity(tableName = "pokemon")
data class PokemonEntity(
    @PrimaryKey
    var id : String,
    val name : String,
    val image : String? = null
) : Serializable

fun Pokemon.toDBPokemon() : PokemonEntity{
    val values = this.detailURL?.split("/")?.toTypedArray()?.filter { it.isNotEmpty() }

    return PokemonEntity(
        id = values?.lastOrNull() ?: System.currentTimeMillis().toString(),
        name = this.name,
        image = this.sprites?.other?.home?.frontDefault
    )
}

fun List<Pokemon>.toPokemonDBList() : List<PokemonEntity>{
    return this.map {
        it.toDBPokemon()
    }
}