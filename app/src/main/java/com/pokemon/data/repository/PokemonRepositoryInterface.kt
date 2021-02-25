package com.pokemon.data.repository

import com.pokemon.data.model.api.Pokemon
import com.pokemon.data.model.db.PokemonEntity
import io.reactivex.Observable
import io.reactivex.Single

interface PokemonRepositoryInterface {
    fun getPokemon(id: String) : Single<Pokemon>
    suspend fun getPokemonFromDb(id: String, name: String) : Observable<List<PokemonEntity>>
    suspend fun getAllPokemonFromDb() : Observable<List<PokemonEntity>>
}