package com.pokemon.data.model.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PokemonDao {
    @Query("SELECT * FROM PokemonEntity")
    suspend fun getAll() : List<PokemonEntity>

    @Query("SELECT * FROM PokemonEntity WHERE id = :id OR name = :name")
    suspend fun getByIdOrName(id: String, name: String) : List<PokemonEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    suspend fun save(pokemon: PokemonEntity)
}