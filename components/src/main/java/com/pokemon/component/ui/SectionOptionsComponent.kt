package com.pokemon.component.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.pokemon.component.R
import com.pokemon.component.databinding.ComponentSectionOptionsBinding

class SectionOptionsComponent(context: Context?, attrs: AttributeSet?)
    : LinearLayout(context, attrs) {

    private var viewLayout = View.inflate(context, R.layout.component_section_options, this)
    private var binding = ComponentSectionOptionsBinding.bind(viewLayout)

    fun updateSection(text: String, items: List<String>) {
        binding.label.text = text
        binding.flexOptionComponent.items = items
    }
}