package com.pokemon.data.model.api

import com.google.gson.annotations.SerializedName

data class PokemonType (
    @SerializedName("slot")
    val slot: Int,
    @SerializedName("type")
    val type: NamedAPIResource
)
