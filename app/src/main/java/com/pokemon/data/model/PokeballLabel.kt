package com.pokemon.data.model

import com.pokemon.ui.component.PokeballLabelComponent

data class PokeballLabel(
    val id: Int,
    val label: String,
    val listener: PokeballLabelComponent.Listener? = null
)