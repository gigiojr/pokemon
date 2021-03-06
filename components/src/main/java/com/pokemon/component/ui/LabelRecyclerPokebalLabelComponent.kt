package com.pokemon.component.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.pokemon.component.R
import com.pokemon.component.databinding.ComponentLabelRecyclerPokeballLabelBinding
import com.pokemon.component.model.PokeballLabel
import com.pokemon.ui.component.recyclerview.PokeballLabelRecyclerAdapter

class LabelRecyclerPokebalLabelComponent(context: Context, attrs: AttributeSet?)
    : ConstraintLayout(context, attrs) {

    private var viewLayout = View.inflate(context,
        R.layout.component_label_recycler_pokeball_label, this)
    private var binding = ComponentLabelRecyclerPokeballLabelBinding.bind(viewLayout)

    init {
        initComponent()
    }

    private fun initComponent() {
        binding.layout.visibility = GONE
        binding.recycler.layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL, false)
    }

    fun setLabel(text: String) {
        binding.label.text = text
    }

    fun setList(list: List<PokeballLabel>) {
        binding.layout.visibility = if(list.isEmpty()) GONE else VISIBLE
        binding.recycler.adapter = PokeballLabelRecyclerAdapter(list)
    }
}