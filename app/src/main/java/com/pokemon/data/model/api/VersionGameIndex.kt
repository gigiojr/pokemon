package com.pokemon.data.model.api

import com.google.gson.annotations.SerializedName

data class VersionGameIndex (
    @SerializedName("game_index")
    val gameIndex : Int,
    @SerializedName("version")
    val version: NamedAPIResource
)
