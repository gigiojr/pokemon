package com.pokemon.data.service

import com.pokemon.data.model.api.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface  PokemonService {
    @GET("pokemon/{id}")
    fun getPokemonById(@Path("id") pokemonId: String) : Call<Pokemon>
}