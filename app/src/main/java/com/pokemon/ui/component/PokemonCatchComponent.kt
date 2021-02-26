package com.pokemon.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.pokemon.R
import com.pokemon.data.model.api.Pokemon
import com.pokemon.databinding.ComponentPokemonCatchBinding
import com.squareup.picasso.Picasso

class PokemonCatchComponent(context: Context, attrs: AttributeSet?)
    : ConstraintLayout(context, attrs) {

    private var viewLayout = View.inflate(context, R.layout.component_pokemon_catch, this)
    private var binding = ComponentPokemonCatchBinding.bind(viewLayout)

    private var pokemon: Pokemon? = null

    fun showPokemonCatch(p: Pokemon){
        pokemon = p
        binding.detailLayout.visibility = View.VISIBLE

        Picasso.get()
            .load(p.sprites?.frontDefault)
            .error(R.drawable.ic_image_not_found)
            .into(binding.pokemonImage)

        binding.title.text = resources.getString(R.string.component_pokemon_found_catch)
            .replace("#", p.name)

        binding.labelHeight.text = resources.getString(R.string.component_pokemon_found_label_height)
            .replace("#", p.height.toString())

        binding.labelWeight.text = resources.getString(R.string.component_pokemon_found_label_weight)
            .replace("#", p.weight.toString())

        binding.abilitiesLayout.updateSection(
            text = resources.getString(R.string.component_pokemon_found_subtitle_abilities),
            items = if(p.abilities != null && p.abilities.isNotEmpty())
                p.abilities.map { it.ability.name }
                else listOf("empty")
        )

        binding.movesLayout.updateSection(
                text = resources.getString(R.string.component_pokemon_found_subtitle_moves),
                items = if(p.moves != null && p.moves.isNotEmpty())
                    p.moves.map { it.move.name }
                else listOf("empty")
        )

        binding.typesLayout.updateSection(
            text = resources.getString(R.string.component_pokemon_found_subtitle_types),
            items = if(p.types != null && p.types.isNotEmpty())
                p.types.map { it.type.name }
                else listOf("empty")
        )

        binding.heldItemsLayout.updateSection(
            text = resources.getString(R.string.component_pokemon_found_subtitle_held_items),
            items = if(p.heldItems != null && p.heldItems.isNotEmpty())
                p.heldItems.map { it.item.name }
                else listOf("empty")
        )

        viewLayout.requestLayout()
    }
}