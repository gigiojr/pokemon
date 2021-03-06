package com.pokemon.component.searchbarcomponent

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.pokemon.component.ui.SearchBarComponent

class SearchBarComponentTestActivity : AppCompatActivity() {

    lateinit var componentView: SearchBarComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        componentView = SearchBarComponent(context = this, attrs = null)

        addContentView(componentView, ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.MATCH_PARENT
        ))
    }
}