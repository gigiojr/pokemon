package com.pokemon.component.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.pokemon.component.R
import com.pokemon.component.databinding.ComponentFlexOptionsBinding
import com.pokemon.component.databinding.ComponentFlexOptionsTextBinding

class FlexOptionsComponent(context: Context, attrs: AttributeSet?)
    : ConstraintLayout(context, attrs) {

    private var viewLayout = View.inflate(context, R.layout.component_flex_options, this)
    private var binding = ComponentFlexOptionsBinding.bind(viewLayout)

    var items: List<String>? = null
        set(value) {
            value?.let { list ->
                list.forEach {
                    val layout = View.inflate(context, R.layout.component_flex_options_text,
                        null)
                    val bind = ComponentFlexOptionsTextBinding.bind(layout)
                    bind.flexOptionText.text = it
                    binding.flexboxLayout.addView(layout)
                }
                viewLayout?.requestLayout()
            }
            field = value
        }

}