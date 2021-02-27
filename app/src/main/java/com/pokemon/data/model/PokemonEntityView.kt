package com.pokemon.data.model

import com.pokemon.data.model.db.PokemonEntity

data class PokemonEntityView(
        val pokemonList: List<PokemonEntity>,
        val error: PokemonError? = null
)
