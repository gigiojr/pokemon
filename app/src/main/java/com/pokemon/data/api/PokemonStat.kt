package com.pokemon.data.model.api

import com.google.gson.annotations.SerializedName

data class PokemonStat (
    @SerializedName("stat")
    val stat: NamedAPIResource,
    @SerializedName("effort")
    val effort: Int,
    @SerializedName("base_stat")
    val baseStat: Int
)
