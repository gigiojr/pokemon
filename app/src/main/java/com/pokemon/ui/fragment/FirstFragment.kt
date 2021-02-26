package com.pokemon.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.pokemon.R
import com.pokemon.component.model.PokeballLabel

import com.pokemon.component.ui.ImageLabelComponent
import com.pokemon.component.ui.PokemonFoundComponent
import com.pokemon.component.ui.SearchBarComponent
import com.pokemon.data.model.api.Pokemon
import com.pokemon.data.model.db.PokemonEntity
import com.pokemon.data.viewmodel.MainViewModel
import com.pokemon.databinding.FragmentFirstBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstFragment : Fragment(), PokemonFoundComponent.Listener,
        SearchBarComponent.Listener, ImageLabelComponent.Listener {

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
        viewModel.pokemonCatch.observe(viewLifecycleOwner, { updatePokemonsCatch(it) })
        viewModel.getAllLocalPokemon()
    }

    override fun onClick(id: Int?, text: String?) {
        text?.let { onSearch(text) }
    }

    override fun onSearchClick(text: String) {
        onSearch(text)
    }

    private fun onSearch(text: String) {
        if (text.isEmpty()){
            binding.searchBarComponent.showErrorText(
                getString(R.string.fragment_first_error_empty_search))
        } else {
            binding.searchBarComponent.hideErrorText()
            viewModel.getPokemon(text)
        }
    }

    private fun updatePokemonsCatch(listCatch: List<PokemonEntity>) {
        val list = listCatch.map { PokeballLabel(it.id, it.name, it.image, this) }
        binding.recyclerComponent.setList(list)
    }

    private fun foundPokemon(pokemon: Pokemon?) {
        pokemon?.let {
            binding.pokemonComponent.setImage(pokemon.sprites?.frontDefault ?: "")
            binding.pokemonComponent.setTitleFound(pokemon.name)
            binding.pokemonComponent.visibility = View.VISIBLE
        } ?: apply {
            binding.pokemonComponent.visibility = View.GONE
        }
        binding.searchBarComponent.clearField()
    }

    override fun onCatchClick() {
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
    }

    private fun showLoad() {
        binding.searchBarComponent.showLoad()
    }

    private fun hideLoad() {
        binding.searchBarComponent.hideLoad()
    }
}