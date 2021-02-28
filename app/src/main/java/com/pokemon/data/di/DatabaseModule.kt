package com.pokemon.data.di

import android.content.Context
import androidx.room.Room
import com.pokemon.data.model.db.PokemonDao
import com.pokemon.data.model.db.PokemonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun providesDatabaseInstance(@ApplicationContext appContext: Context): PokemonDatabase {
        return Room
            .databaseBuilder(appContext, PokemonDatabase::class.java, "pokemon_database")
            .build()
    }

    @Provides
    fun providesPokemonDao(appDatabase: PokemonDatabase) : PokemonDao{
        return appDatabase.pokemonDao()
    }
}