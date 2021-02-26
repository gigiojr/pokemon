package com.pokemon.component.model

import com.pokemon.component.ui.ImageLabelComponent

data class PokeballLabel(
    val id: Int,
    val label: String,
    val image: String? = null,
    val listener: ImageLabelComponent.Listener? = null
)