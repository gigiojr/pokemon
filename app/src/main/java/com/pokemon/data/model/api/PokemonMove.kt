package com.pokemon.data.model.api

import com.google.gson.annotations.SerializedName

data class PokemonMove (
    @SerializedName("move")
    val move: NamedAPIResource,
    @SerializedName("version_group_details")
    val versionGroupDetails: List<PokemonMoveVersion>
)
