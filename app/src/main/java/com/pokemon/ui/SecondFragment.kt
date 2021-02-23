package com.pokemon.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.pokemon.R
import com.pokemon.data.model.api.Pokemon
import com.pokemon.data.viewmodel.MainViewModel
import com.pokemon.databinding.FragmentSecondBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondFragment : Fragment(), View.OnClickListener {

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

    private fun initView() {
        binding.btBack.setOnClickListener(this)
        binding.pokemonComponent.visibility = View.GONE
        viewModel.pokemonFound.observe(viewLifecycleOwner, { foundPokemon(it) })
    }

    private fun foundPokemon(pokemon: Pokemon) {
        binding.pokemonComponent.visibility = View.VISIBLE
        binding.pokemonComponent.showPokemonCatch(pokemon)
    }

    override fun onClick(v: View?) {
        findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
    }
}