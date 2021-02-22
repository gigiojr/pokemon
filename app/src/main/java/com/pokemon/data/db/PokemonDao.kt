package com.pokemon.data.model.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable

@Dao
interface PokemonDao {
    @Query("SELECT * FROM PokemonEntity WHERE id = :id OR name = :id")
    fun getByIdOrName(id: String) : LiveData<PokemonEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    fun save(pokemon: PokemonEntity) : Completable
}