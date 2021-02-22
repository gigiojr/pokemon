package com.pokemon.data.model.api

import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("base_experience")
    val baseExperience: Int,
    @SerializedName("height")
    val height: Int,
    @SerializedName("is_default")
    val isDefault: Boolean,
    @SerializedName("order")
    val order: Int,
    @SerializedName("weight")
    val weight: Int,
    @SerializedName("abilities")
    val abilities: List<PokemonAbility>?,
    @SerializedName("forms")
    val forms: List<NamedAPIResource>?,
    @SerializedName("game_indices")
    val gameIndices: List<VersionGameIndex>?,
    @SerializedName("held_items")
    val heldItems: List<PokemonHeldItem>?,
    @SerializedName("location_area_encounters")
    val locationAreaEncounters: String?,
    @SerializedName("moves")
    val moves: List<PokemonMove>?,
    @SerializedName("sprites")
    val sprites: PokemonSprites?,
    @SerializedName("species")
    val species: NamedAPIResource?,
    @SerializedName("stats")
    val stats: List<PokemonStat>?,
    @SerializedName("types")
    val types: List<PokemonType>?,
)
