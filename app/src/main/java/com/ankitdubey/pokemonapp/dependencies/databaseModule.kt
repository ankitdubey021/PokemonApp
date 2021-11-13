package com.ankitdubey.pokemonapp.dependencies

import android.content.Context
import androidx.room.Room
import com.ankitdubey.pokemonapp.database.PokemonDao
import com.ankitdubey.pokemonapp.database.PokemonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Ankit Dubey on 13,November,2021
 */


@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun providePokemonDB(@ApplicationContext context: Context): PokemonDatabase {
        return PokemonDatabase.getDatabase(context)
    }

    @Singleton
    @Provides
    fun providePokemonDao(database: PokemonDatabase) : PokemonDao{
        return database.pokemonDao()
    }
}