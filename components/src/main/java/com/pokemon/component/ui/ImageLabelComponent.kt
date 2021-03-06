package com.pokemon.component.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.pokemon.component.R
import com.pokemon.component.databinding.ComponentImageLabelBinding
import com.squareup.picasso.Picasso

class ImageLabelComponent(context: Context, attrs: AttributeSet?)
    : ConstraintLayout(context, attrs), View.OnClickListener {

    private var viewLayout = View.inflate(context, R.layout.component_image_label, this)
    private var binding = ComponentImageLabelBinding.bind(viewLayout)

    var id: Int? = null
    var image: String? = null
        set(value) {
            Picasso.get()
                .load(value)
                .error(R.drawable.ic_pokeball)
                .into(binding.imageView)
            field = value
        }

    var text: String? = null
        set(value) {
            binding.label.text = value ?: ""
            field = value
        }

    var listener: Listener? = null

    init {
        initComponent()
    }

    private fun initComponent() {
        binding.pokeballLabelLayout.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        listener?.onClick(id, text)
    }

    interface Listener {
        fun onClick(id: Int?, text: String?)
    }
}