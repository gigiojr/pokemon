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

    fun showPokemon(pokemon: Pokemon) {
        Picasso.get()
            .load(pokemon.sprites?.frontDefault)
            .error(R.drawable.ic_image_not_found)
            .into(binding.pokemonImage)

        binding.title.text = resources.getString(R.string.first_fragment_title_name)
            .replace("#", pokemon.name)

        viewLayout.requestLayout()
    }

    override fun onClick(v: View?) {
        pokemon?.let { p -> listener?.onCatchClick(p) }
    }

    interface Listener {
        fun onCatchClick(pokemon: Pokemon)
    }
}