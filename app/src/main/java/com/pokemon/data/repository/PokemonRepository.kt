package com.pokemon.data.repository

import com.pokemon.data.model.api.Pokemon
import com.pokemon.data.model.db.PokemonDao
import com.pokemon.data.model.db.PokemonMapper
import com.pokemon.data.service.PokemonService
import io.reactivex.Single
import io.reactivex.subjects.SingleSubject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val service: PokemonService,
    private val pokemonDao: PokemonDao
) : PokemonRepositoryInterface {

    override fun getPokemon(id: String): Single<Pokemon> {
        val single = SingleSubject.create<Pokemon>()
        service.getPokemonById(id).enqueue(object : Callback<Pokemon?> {
            override fun onResponse(call: Call<Pokemon?>?, response: Response<Pokemon?>) {
                val pokemon: Pokemon? = response.body()
                if (pokemon != null) {
                    pokemonDao.save(PokemonMapper.transformTo(pokemon))
                    single.onSuccess(pokemon)
                } else {
                    val pokemonEntity = pokemonDao.getByIdOrName(id)
                    pokemonEntity.value?.let {
                         PokemonMapper.transformTo(it)
                    } ?: apply {
                        single.onError(Throwable("Pokemon not found"))
                    }
                }
            }

            override fun onFailure(call: Call<Pokemon?>?, t: Throwable) {
                val pokemonEntity = pokemonDao.getByIdOrName(id)
                pokemonEntity.value?.let {
                    PokemonMapper.transformTo(it)
                } ?: apply {
                    single.onError(t)
                }
            }
        })
        return single
    }
}