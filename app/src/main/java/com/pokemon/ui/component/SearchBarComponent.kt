package com.pokemon.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.constraintlayout.widget.ConstraintLayout
import com.pokemon.R
import com.pokemon.databinding.ComponentSearchBarBinding

class SearchBarComponent(context: Context, attrs: AttributeSet?)
    : ConstraintLayout(context, attrs), View.OnClickListener {

    private var viewLayout = View.inflate(context, R.layout.component_search_bar, this)
    private var binding = ComponentSearchBarBinding.bind(viewLayout)

    var listener: Listener? = null

    init {
        initComponent()
    }

    private fun initComponent() {
        binding.progressBar.visibility = View.GONE
        binding.btSearch.setOnClickListener(this)
        binding.searchField.setOnKeyListener(OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                onClick(v)
                return@OnKeyListener true
            }
            false
        })
    }

    override fun onClick(v: View?) {
        val imm: InputMethodManager? = v?.context
            ?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm?.hideSoftInputFromWindow(v?.windowToken, 0)

        listener?.onSearchClick(binding.searchField.text.toString())
    }

    fun clearField() {
        binding.searchField.text.clear()
    }

    fun showLoad() {
        binding.btSearch.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
    }

    fun hideLoad() {
        binding.btSearch.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE
    }

    interface Listener {
        fun onSearchClick(text: String)
    }
}