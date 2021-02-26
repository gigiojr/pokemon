package com.pokemon.component.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.pokemon.component.R
import com.pokemon.component.databinding.ComponentPokemonFoundBinding
import com.squareup.picasso.Picasso

class PokemonFoundComponent(context: Context, attrs: AttributeSet?)
    : ConstraintLayout(context, attrs), View.OnClickListener {

    private var viewLayout = View.inflate(context, R.layout.component_pokemon_found, this)
    private var binding = ComponentPokemonFoundBinding.bind(viewLayout)

    var listener: Listener? = null

    init {
        binding.btCatch.setOnClickListener(this)
        showButton()
    }

    fun setImage(url: String) {
        Picasso.get()
            .load(url)
            .error(R.drawable.ic_image_not_found)
            .into(binding.pokemonImage)
    }

    fun setTitleFound(title: String) {
        binding.title.text = resources
            .getString(R.string.component_pokemon_found_found)
            .replace("#", title)
    }

    fun setTitleCatch(title: String) {
        binding.title.text = resources
            .getString(R.string.component_pokemon_found_catch)
            .replace("#", title)
    }

    fun showButton() {
        binding.btCatch.visibility = View.VISIBLE
    }

    fun hideButton() {
        binding.btCatch.visibility = View.GONE
    }

    override fun onClick(v: View?) {
        listener?.onCatchClick()
    }

    interface Listener {
        fun onCatchClick()
    }
}