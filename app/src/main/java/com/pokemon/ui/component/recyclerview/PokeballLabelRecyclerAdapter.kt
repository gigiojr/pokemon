package com.pokemon.ui.component.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pokemon.data.model.PokeballLabel
import com.pokemon.ui.component.PokeballLabelComponent

class PokeballLabelRecyclerAdapter(private val dataSet: List<PokeballLabel>) :
    RecyclerView.Adapter<PokeballLabelRecyclerAdapter.ViewHolder>() {

    class ViewHolder(view: PokeballLabelComponent) : RecyclerView.ViewHolder(view) {
        val component: PokeballLabelComponent = view
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val component = PokeballLabelComponent(viewGroup.context, null)
        return ViewHolder(component)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.component.id = dataSet[position].id
        viewHolder.component.text = dataSet[position].label
        viewHolder.component.listener = dataSet[position].listener
    }

    override fun getItemCount() = dataSet.size
}