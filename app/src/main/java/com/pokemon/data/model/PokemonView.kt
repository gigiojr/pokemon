package com.pokemon.data.model

import com.pokemon.data.model.api.Pokemon

data class PokemonView(
    val pokemon: Pokemon? = null,
    val error: PokemonError? = null
)
