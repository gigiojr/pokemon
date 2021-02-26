package com.pokemon.component.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.pokemon.component.R
import com.pokemon.component.databinding.ComponentPokemonCatchBinding

class PokemonCatchComponent(context: Context, attrs: AttributeSet?)
    : ConstraintLayout(context, attrs) {

    private var viewLayout = View.inflate(context, R.layout.component_pokemon_catch, this)
    private var binding = ComponentPokemonCatchBinding.bind(viewLayout)

    fun setPokemonHeight(height: Int) {
        binding.labelHeight.text = resources
            .getString(R.string.component_pokemon_found_label_height)
            .replace("#", height.toString())
    }

    fun setPokemonWeight(weight: Int) {
        binding.labelWeight.text = resources
            .getString(R.string.component_pokemon_found_label_weight)
            .replace("#", weight.toString())
    }

    fun setAbilities(list: List<String>) =
        binding.abilitiesLayout.updateSection(
            text = resources.getString(R.string.component_pokemon_found_subtitle_abilities),
            items = list
        )

    fun setMoves(list: List<String>) =
        binding.movesLayout.updateSection(
            text = resources.getString(R.string.component_pokemon_found_subtitle_moves),
            items = list
        )

    fun setTypes(list: List<String>) =
        binding.typesLayout.updateSection(
            text = resources.getString(R.string.component_pokemon_found_subtitle_types),
            items = list
        )

    fun setHeldItems(list: List<String>) =
        binding.heldItemsLayout.updateSection(
            text = resources.getString(R.string.component_pokemon_found_subtitle_held_items),
            items = list
        )
}