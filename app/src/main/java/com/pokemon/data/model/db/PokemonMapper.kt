package com.pokemon.data.model.db

import com.pokemon.data.model.api.Pokemon
import com.pokemon.data.model.api.PokemonSprites

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
            locationAreaEncounters = source.locationAreaEncounters,
            image = source.sprites?.frontDefault
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
            sprites = PokemonSprites(source.image, null, null,
                    null, null, null,
                    null, null),
            species = null,
            stats = emptyList(),
            types = emptyList()
        )
    }
}