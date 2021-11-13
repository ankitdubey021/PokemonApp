package com.ankitdubey.pokemonapp.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by Ankit Dubey on 13,November,2021
 */


@Entity(tableName = "pokemon")
data class Pokemon(
    @PrimaryKey
    var id : String,
    val name : String,
    /*@Ignore val height : Int ? = null,
    @Ignore val weight : Int ? = null,*/

    /*@SerializedName("base_experience")
    @Ignore val experience : Int ? = null,*/

    @SerializedName("url")
    val detailURL : String
)

data class PokemonList(
    val results : List<Pokemon>,
    val count : Long
)
