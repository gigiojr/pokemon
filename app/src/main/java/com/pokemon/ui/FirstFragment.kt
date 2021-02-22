package com.pokemon.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.pokemon.data.model.api.Pokemon
import com.pokemon.data.viewmodel.MainViewModel
import com.pokemon.databinding.FragmentFirstBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFirstBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        binding.btSearch.setOnClickListener { onSearch(it) }
        viewModel.isLoading.observe(viewLifecycleOwner, { if (it) showLoad() else hideLoad() })
        viewModel.pokemonFound.observe(viewLifecycleOwner, { foundPokemon(it) })
    }

    private fun onSearch(view: View) {
        viewModel.getPokemon(binding.searchField.text.toString())
    }

    private fun foundPokemon(pokemon: Pokemon) {
        Toast.makeText(context, pokemon.name, Toast.LENGTH_LONG).show()
    }

    private fun showLoad() {

    }

    private fun hideLoad() {

    }
}