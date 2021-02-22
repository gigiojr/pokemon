package com.pokemon.data.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonAbilityEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val url: String,
    val isHidden: Boolean,
    val slot: Int
)
