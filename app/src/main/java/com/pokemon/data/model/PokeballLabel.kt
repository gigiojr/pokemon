package com.pokemon.data.model

import com.pokemon.ui.component.PokeballLabelComponent

data class PokeballLabel(
    val id: Int,
    val label: String,
    val image: String? = null,
    val listener: PokeballLabelComponent.Listener? = null
)