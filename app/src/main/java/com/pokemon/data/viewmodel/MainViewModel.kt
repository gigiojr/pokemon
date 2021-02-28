package com.pokemon.data.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pokemon.R
import com.pokemon.data.model.PokemonEntityView
import com.pokemon.data.model.PokemonError
import com.pokemon.data.model.PokemonView
import com.pokemon.data.network.NetworkInfo
import com.pokemon.data.util.async
import com.pokemon.data.repository.PokemonRepository
import com.pokemon.data.repository.WebHookRepository
import com.pokemon.data.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (
    @ApplicationContext private val context: Context,
    private val pokemonRepository: PokemonRepository,
    private val webHookRepository: WebHookRepository,
    private val networkInfo: NetworkInfo
) : ViewModel() {

    private val _pokemonFound = MutableLiveData<Resource<PokemonView>>()
    val pokemonFound : LiveData<Resource<PokemonView>> = _pokemonFound

    private val _pokemonFoundFavorite = MutableLiveData<Resource<PokemonView>>()
    val pokemonFoundFavorite : LiveData<Resource<PokemonView>> = _pokemonFoundFavorite

    private val _pokemonCatch = MutableLiveData<Resource<PokemonEntityView>>()
    val pokemonCatch : LiveData<Resource<PokemonEntityView>> = _pokemonCatch

    @SuppressLint("CheckResult")
    fun getPokemon(id: String) {
        pokemonRepository.getPokemon(id)
            .async()
            .doOnSubscribe { _pokemonFound.postValue(Resource.Loading()) }
            .subscribe({ pokemon ->
                _pokemonFound.postValue(Resource.Success(
                    data = PokemonView(
                        pokemon = pokemon
                    )
                ))
            }, { error ->
                error.printStackTrace()
                _pokemonFound.postValue(Resource.Error(
                    data = PokemonView(
                        error = if (networkInfo.isOnline()) getError(error)
                            else getInternetError()
                    )
                ))
            })
    }

    fun catchPokemon() {
        _pokemonFound.value?.data?.pokemon?.let {
            webHookRepository.catchPokemon(it)
                .async()
                .subscribe({
                    // Nothing to do here
                }, { error ->
                    error.printStackTrace()
                })
        }
    }

    fun favoritePokemon() {
        _pokemonFound.value?.data?.pokemon?.let { pokemon ->
            webHookRepository.catchPokemon(pokemon)
                .async()
                .doOnSubscribe { _pokemonFoundFavorite.postValue(Resource.Loading()) }
                .subscribe({
                    _pokemonFoundFavorite.postValue(Resource.Success(
                        data = PokemonView(
                            pokemon = pokemon
                        )
                    ))
                }, { error ->
                    error.printStackTrace()
                    _pokemonFoundFavorite.postValue(Resource.Error(
                        data = PokemonView(
                            error = if (networkInfo.isOnline()) getError(error)
                            else getInternetError()
                        )
                    ))
                })
        }
    }

    fun getAllLocalPokemon(){
        GlobalScope.launch {
            pokemonRepository.getAllPokemonFromDb()
                .doOnSubscribe { _pokemonCatch.postValue(Resource.Loading()) }
                .subscribe({
                    _pokemonCatch.postValue(Resource.Success(
                        data = PokemonEntityView(
                            pokemonList = it
                        )
                    ))
                }, { error ->
                    error.printStackTrace()
                    _pokemonCatch.postValue(Resource.Error(
                        data = PokemonEntityView(
                            pokemonList = emptyList(),
                            error = getError(error)
                        )
                    ))
                })
        }
    }

    fun clearFoundPokemon() {
        _pokemonFound.postValue(Resource.Success(data = PokemonView()))
        _pokemonFoundFavorite.postValue(Resource.Success(data = PokemonView()))
    }

    private fun getError(error: Throwable): PokemonError{
        return  PokemonError(
                title = context.getString(R.string.error_generic_title),
                description = error.message ?: context.getString(R.string.error_generic_description)
        )
    }

    private fun getInternetError(): PokemonError {
        return  PokemonError(
            title = context.getString(R.string.error_network_not_connected_title),
            description = context.getString(R.string.error_network_not_connected_description)
        )
    }
}