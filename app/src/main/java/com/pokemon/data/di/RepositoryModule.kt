package com.pokemon.data.di

import com.pokemon.data.repository.PokemonRepositoryImp
import com.pokemon.data.repository.PokemonRepository
import com.pokemon.data.repository.WebHookRepository
import com.pokemon.data.repository.WebHookRepositoryImp
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
        pokemonRepositoryImp: PokemonRepositoryImp
    ): PokemonRepository

    @Singleton
    @Binds
    abstract fun bindWebHookRepository(
        webHookRepository: WebHookRepositoryImp
    ): WebHookRepository
}