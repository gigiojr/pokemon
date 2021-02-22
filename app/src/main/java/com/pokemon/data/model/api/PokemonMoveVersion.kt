package com.pokemon.data.model.api

import com.google.gson.annotations.SerializedName

data class PokemonMoveVersion (
    @SerializedName("move_learn_method")
    val moveLearnMethod: NamedAPIResource,
    @SerializedName("version_group")
    val versionGroup: NamedAPIResource,
    @SerializedName("level_learned_at")
    val levelLearnedAt: Int,
)
