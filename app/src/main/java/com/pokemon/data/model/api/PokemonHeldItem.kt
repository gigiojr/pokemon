package com.pokemon.data.model.api

import com.google.gson.annotations.SerializedName

data class PokemonHeldItem (
    @SerializedName("item")
    val item: NamedAPIResource,
    @SerializedName("version_details")
    val versionDetails: List<PokemonHeldItemVersion>
)
