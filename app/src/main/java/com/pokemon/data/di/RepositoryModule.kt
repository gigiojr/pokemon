package com.pokemon.data.di

import com.pokemon.data.repository.PokemonRepository
import com.pokemon.data.repository.PokemonRepositoryInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindPokemonRepository(
        pokemonRepository: PokemonRepository
    ): PokemonRepositoryInterface
}