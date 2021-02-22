package com.pokemon.data.repository

import com.pokemon.data.model.api.Pokemon
import io.reactivex.Single

interface PokemonRepositoryInterface {
    fun getPokemon(id: String) : Single<Pokemon>
}