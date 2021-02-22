package com.pokemon.data.model.api

import com.google.gson.annotations.SerializedName

data class PokemonAbility (
    @SerializedName("ability")
    val ability: NamedAPIResource,
    @SerializedName("is_hidden")
    val isHidden: Boolean,
    @SerializedName("slot")
    val slot: Int
)
