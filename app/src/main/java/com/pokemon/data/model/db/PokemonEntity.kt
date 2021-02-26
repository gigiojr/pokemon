package com.pokemon.data.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonEntity (
    @PrimaryKey val id: Int,
    val name: String,
    val baseExperience: Int,
    val height: Int,
    val isDefault: Boolean,
    val order: Int,
    val weight: Int,
    val locationAreaEncounters: String?,
    val image: String?
)
