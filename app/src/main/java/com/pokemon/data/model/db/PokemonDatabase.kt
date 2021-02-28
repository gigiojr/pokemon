package com.pokemon.data.model.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [
    PokemonEntity::class,
    PokemonAbilityEntity::class,
    NamedAPIResourceEntity::class
], version = 1)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao() : PokemonDao
}