package com.pokemon.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.pokemon.R
import com.pokemon.component.ui.ImageLabelComponent
import com.pokemon.data.model.PokemonView
import com.pokemon.data.model.api.Pokemon
import com.pokemon.data.util.Dialog
import com.pokemon.data.util.Resource
import com.pokemon.data.viewmodel.MainViewModel
import com.pokemon.databinding.FragmentSecondBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondFragment : Fragment(), ImageLabelComponent.Listener {

    private lateinit var binding: FragmentSecondBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSecondBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.clearFoundPokemon()
    }

    private fun initView() {
        hideLoad()
        binding.imageLabelComponent.visibility = View.VISIBLE
        binding.pokemonFoundComponent.hideButton()
        binding.imageLabelComponent.text = getString(R.string.fragment_second_favorite)
        binding.imageLabelComponent.listener = this
        viewModel.pokemonFound.observe(viewLifecycleOwner, this::pokemonFoundObserver)
        viewModel.pokemonFoundFavorite.observe(viewLifecycleOwner,
            this::pokemonFoundFavoriteObserver)
    }

    private fun pokemonFoundObserver(resource: Resource<PokemonView>) {
        when (resource) {
            is Resource.Loading -> { showLoad() }
            is Resource.Error -> {
                hideLoad()
                resource.data?.error?.let {
                Dialog.showAlert(context, it.title, it.description) }}
            is Resource.Success -> {
                hideLoad()
                resource.data?.pokemon?.let { foundPokemon(it) }
            }
        }
    }

    private fun pokemonFoundFavoriteObserver(resource: Resource<PokemonView>) {
        when (resource) {
            is Resource.Loading -> { showLoad() }
            is Resource.Error -> {
                hideLoad()
                resource.data?.error?.let {
                    Dialog.showAlert(context, it.title, it.description) }}
            is Resource.Success -> {
                hideLoad()
                resource.data?.pokemon?.let {
                    binding.imageLabelComponent.visibility = View.GONE
                }
            }
        }
    }

    private fun foundPokemon(pokemon: Pokemon) {
        binding.pokemonFoundComponent.setImage(pokemon.sprites?.frontDefault ?: "")
        binding.pokemonFoundComponent.setTitleCatch(pokemon.name)

        binding.pokemonCatchComponent.setPokemonHeight(pokemon.height)
        binding.pokemonCatchComponent.setPokemonWeight(pokemon.weight)

        binding.pokemonCatchComponent.setAbilities(
            if(pokemon.abilities != null && pokemon.abilities.isNotEmpty())
                pokemon.abilities.map { it.ability.name }
            else listOf("empty")
        )

        binding.pokemonCatchComponent.setMoves(
            if(pokemon.moves != null && pokemon.moves.isNotEmpty())
                pokemon.moves.map { it.move.name }
            else listOf("empty")
        )

        binding.pokemonCatchComponent.setTypes(
            if(pokemon.types != null && pokemon.types.isNotEmpty())
                pokemon.types.map { it.type.name }
            else listOf("empty")
        )

        binding.pokemonCatchComponent.setHeldItems(
            if(pokemon.heldItems != null && pokemon.heldItems.isNotEmpty())
                pokemon.heldItems.map { it.item.name }
            else listOf("empty")
        )
    }

    private fun showLoad() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideLoad() {
        binding.progressBar.visibility = View.GONE
    }

    override fun onClick(id: Int?, text: String?) {
        viewModel.favoritePokemon()
    }
}