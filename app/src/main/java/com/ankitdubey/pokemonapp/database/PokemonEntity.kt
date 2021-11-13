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
    val image : String? = null,
    val weight : Int? = null,
    val height : Int? = null
) : Serializable{
    fun getWeightString(): String = String.format("%.1f KG", weight?.toFloat()?:0 / 10)
    fun getHeightString(): String = String.format("%.1f M", height?.toFloat()?:0 / 10)
}

fun Pokemon.toDBPokemon() : PokemonEntity{
    val values = this.detailURL?.split("/")?.toTypedArray()?.filter { it.isNotEmpty() }

    return PokemonEntity(
        id = values?.lastOrNull() ?: System.currentTimeMillis().toString(),
        name = this.name,
        image = this.sprites?.other?.home?.frontDefault,
        weight = this.weight,
        height = this.height
    )
}

fun List<Pokemon>.toPokemonDBList() : List<PokemonEntity>{
    return this.map {
        it.toDBPokemon()
    }
}