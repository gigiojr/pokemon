package com.pokemon.data.model.api

import com.google.gson.annotations.SerializedName

data class PokemonHeldItemVersion (
    @SerializedName("version")
    val version: NamedAPIResource,
    @SerializedName("rarity")
    val rarity: Int
)