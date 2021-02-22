package com.pokemon.data.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.pokemon.data.extension.async
import com.pokemon.data.model.api.Pokemon
import com.pokemon.data.repository.PokemonRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (
    private val pokemonRepository: PokemonRepositoryInterface,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _isLoading : MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading : LiveData<Boolean> = _isLoading

    private val _pokemonFound : MutableLiveData<Pokemon> = MutableLiveData<Pokemon>()
    val pokemonFound : LiveData<Pokemon> = _pokemonFound

    @SuppressLint("CheckResult")
    fun getPokemon (id: String) {
        pokemonRepository.getPokemon(id)
            .async()
            .doOnSubscribe { _isLoading.postValue(true) }
            .subscribe({ pokemon ->
                _pokemonFound.postValue(pokemon)
                _isLoading.postValue(false)
            }, { error ->
                error.printStackTrace()
                _isLoading.postValue(false)
            })
    }
}