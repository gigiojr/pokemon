package com.pokemon.component.flexoptionscomponent

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.pokemon.component.ui.FlexOptionsComponent

class FlexOptionsComponentTestActivity : AppCompatActivity() {

    lateinit var componentView: FlexOptionsComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        componentView = FlexOptionsComponent(context = this, attrs = null)

        addContentView(componentView, ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.MATCH_PARENT
        ))
    }
}