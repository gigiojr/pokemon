package com.pokemon.ui.component.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pokemon.component.ui.ImageLabelComponent
import com.pokemon.component.model.PokeballLabel

class PokeballLabelRecyclerAdapter(private val dataSet: List<PokeballLabel>) :
    RecyclerView.Adapter<PokeballLabelRecyclerAdapter.ViewHolder>() {

    class ViewHolder(view: ImageLabelComponent) : RecyclerView.ViewHolder(view) {
        val component: ImageLabelComponent = view
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val component = ImageLabelComponent(viewGroup.context, null)
        return ViewHolder(component)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.component.id = dataSet[position].id
        viewHolder.component.text = dataSet[position].label
        viewHolder.component.image = dataSet[position].image
        viewHolder.component.listener = dataSet[position].listener
    }

    override fun getItemCount() = dataSet.size
}