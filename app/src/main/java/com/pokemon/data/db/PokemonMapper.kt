package com.pokemon.data.model.db

import com.pokemon.data.model.api.Pokemon

object PokemonMapper {
    fun transformTo(source: Pokemon) : PokemonEntity {
        return PokemonEntity(
            id = source.id,
            name = source.name,
            baseExperience = source.baseExperience,
            height = source.height,
            isDefault = source.isDefault,
            order = source.order,
            weight = source.weight,
            locationAreaEncounters = source.locationAreaEncounters
        )
    }

    fun transformTo(source: PokemonEntity) : Pokemon {
        return Pokemon(
            id = source.id,
            name = source.name,
            baseExperience = source.baseExperience,
            height = source.height,
            isDefault = source.isDefault,
            order = source.order,
            weight = source.weight,
            locationAreaEncounters = source.locationAreaEncounters,
            abilities = emptyList(),
            forms = emptyList(),
            gameIndices = emptyList(),
            heldItems = emptyList(),
            moves = emptyList(),
            sprites = null,
            species = null,
            stats = emptyList(),
            types = emptyList()
        )
    }
}