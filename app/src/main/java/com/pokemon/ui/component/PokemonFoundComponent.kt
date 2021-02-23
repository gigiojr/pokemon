package com.pokemon.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.pokemon.R
import com.pokemon.data.model.api.Pokemon
import com.pokemon.databinding.ComponentPokemonFoundBinding
import com.squareup.picasso.Picasso

class PokemonFoundComponent(context: Context, attrs: AttributeSet?)
    : ConstraintLayout(context, attrs), View.OnClickListener {

    private var viewLayout = View.inflate(context, R.layout.component_pokemon_found, this)
    private var binding = ComponentPokemonFoundBinding.bind(viewLayout)

    private var pokemon: Pokemon? = null

    var listener: Listener? = null

    init {
        binding.btCatch.setOnClickListener(this)
    }

    fun showPokemonFound(pokemon: Pokemon) {
        this.pokemon = pokemon
        binding.detailLayout.visibility = View.GONE

        Picasso.get()
            .load(pokemon.sprites?.frontDefault)
            .error(R.drawable.ic_image_not_found)
            .into(binding.pokemonImage)

        binding.title.text = resources.getString(R.string.component_pokemon_found_found)
            .replace("#", pokemon.name)

        showCatchButton()

        viewLayout.requestLayout()
    }

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

        if (p.abilities != null && p.abilities.isNotEmpty()){
            binding.abilitiesLayout.updateSection(
                text = resources.getString(R.string.component_pokemon_found_subtitle_abilities),
                items = p.abilities.map { it.ability.name }
            )
            binding.abilitiesLayout.visibility = View.VISIBLE
        } else {
            binding.abilitiesLayout.visibility = View.GONE
        }

        if(p.types != null && p.types.isNotEmpty()){
            binding.typesLayout.updateSection(
                text = resources.getString(R.string.component_pokemon_found_subtitle_types),
                items = p.types.map { it.type.name }
            )
            binding.abilitiesLayout.visibility = View.VISIBLE
        } else {
            binding.typesLayout.visibility = View.GONE
        }

        if(p.heldItems != null && p.heldItems.isNotEmpty()){
            binding.heldItemsLayout.updateSection(
                text = resources.getString(R.string.component_pokemon_found_subtitle_held_items),
                items = p.heldItems.map { it.item.name }
            )
            binding.heldItemsLayout.visibility = View.VISIBLE
        } else {
            binding.heldItemsLayout.visibility = View.GONE
        }

        hideCatchButton()

        viewLayout.requestLayout()
    }

    override fun onClick(v: View?) {
        pokemon?.let { p -> listener?.onCatchClick(p) }
    }

    fun showCatchButton() {
        binding.btCatch.visibility = View.VISIBLE
    }

    fun hideCatchButton() {
        binding.btCatch.visibility = View.GONE
    }

    interface Listener {
        fun onCatchClick(pokemon: Pokemon)
    }
}