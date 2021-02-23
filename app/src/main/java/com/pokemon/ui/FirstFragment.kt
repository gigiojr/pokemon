package com.pokemon.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.pokemon.R
import com.pokemon.data.model.api.Pokemon
import com.pokemon.data.viewmodel.MainViewModel
import com.pokemon.databinding.FragmentFirstBinding
import com.pokemon.ui.component.PokemonFoundComponent
import com.pokemon.ui.component.SearchBarComponent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstFragment : Fragment(), PokemonFoundComponent.Listener, SearchBarComponent.Listener {

    private lateinit var binding: FragmentFirstBinding
    private val viewModel: MainViewModel by activityViewModels()

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
        hideLoad()
        binding.pokemonComponent.visibility = View.GONE
        binding.pokemonComponent.listener = this
        binding.searchBarComponent.listener = this

        viewModel.isLoading.observe(viewLifecycleOwner, { if (it) showLoad() else hideLoad() })
        viewModel.pokemonFound.observe(viewLifecycleOwner, { foundPokemon(it) })
    }

    override fun onSearchClick(text: String) {
        onSearch(text)
    }

    private fun onSearch(text: String) {
        viewModel.getPokemon(text)
    }

    private fun foundPokemon(pokemon: Pokemon) {
        binding.pokemonComponent.showPokemonFound(pokemon)
        binding.pokemonComponent.visibility = View.VISIBLE
        binding.searchBarComponent.clearField()
    }

    override fun onCatchClick(pokemon: Pokemon) {
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
    }

    private fun showLoad() {
        binding.searchBarComponent.showLoad()
    }

    private fun hideLoad() {
        binding.searchBarComponent.hideLoad()
    }
}