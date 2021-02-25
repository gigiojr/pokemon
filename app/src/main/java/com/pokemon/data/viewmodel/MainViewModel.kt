package com.pokemon.data.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.pokemon.data.extension.async
import com.pokemon.data.model.api.Pokemon
import com.pokemon.data.model.db.PokemonEntity
import com.pokemon.data.model.db.PokemonMapper
import com.pokemon.data.repository.PokemonRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (
    private val pokemonRepository: PokemonRepositoryInterface
) : ViewModel() {

    private val _isLoading : MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading : LiveData<Boolean> = _isLoading

    private val _isLocal : MutableLiveData<Boolean> = MutableLiveData(false)
    val isLocal : LiveData<Boolean> = _isLocal

    private val _pokemonFound : MutableLiveData<Pokemon> = MutableLiveData<Pokemon>()
    val pokemonFound : LiveData<Pokemon> = _pokemonFound

    private val _pokemonCatch : MutableLiveData<List<PokemonEntity>> = MutableLiveData<List<PokemonEntity>>()
    val pokemonCatch : LiveData<List<PokemonEntity>> = _pokemonCatch

    @SuppressLint("CheckResult")
    fun getPokemon(id: String) {
        pokemonRepository.getPokemon(id)
            .async()
            .doOnSubscribe { _isLoading.postValue(true) }
            .subscribe({ pokemon ->
                _pokemonFound.postValue(pokemon)
                _isLoading.postValue(false)
                _isLocal.postValue(false)
            }, { error ->
                error.printStackTrace()
                _isLoading.postValue(false)
                getLocalPokemon(id)
            })
    }

    @SuppressLint("CheckResult")
    fun getLocalPokemon(id: String) {
        GlobalScope.launch {
            pokemonRepository.getPokemonFromDb(id, id)
                .doOnSubscribe { _isLoading.postValue(true) }
                .subscribe({ pokemonEntity ->
                    pokemonEntity?.let {
                        if (it.isNotEmpty()){
                            _pokemonFound.postValue(PokemonMapper.transformTo(it[0]))
                        }
                    }
                    _isLoading.postValue(false)
                    _isLocal.postValue(true)
                }, { error ->
                    error.printStackTrace()
                    _isLoading.postValue(false)
                })
        }
    }

    fun getAllLocalPokemon(){
        GlobalScope.launch {
            pokemonRepository.getAllPokemonFromDb()
                .doOnSubscribe { _isLoading.postValue(true) }
                .subscribe({
                    _pokemonCatch.postValue(it)
                    _isLoading.postValue(false)
                }, { error ->
                    error.printStackTrace()
                    _isLoading.postValue(false)
                })
        }
    }
}