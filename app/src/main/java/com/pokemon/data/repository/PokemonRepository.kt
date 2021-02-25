package com.pokemon.data.repository

import com.pokemon.data.model.api.Pokemon
import com.pokemon.data.model.db.PokemonDao
import com.pokemon.data.model.db.PokemonEntity
import com.pokemon.data.model.db.PokemonMapper
import com.pokemon.data.service.PokemonService
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.SingleSubject
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
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
                    GlobalScope.launch { pokemonDao.save(PokemonMapper.transformTo(pokemon)) }
                    single.onSuccess(pokemon)
                } else {
                    single.onError(Throwable("Pokemon not found"))
                }
            }

            override fun onFailure(call: Call<Pokemon?>?, t: Throwable) {
                t.printStackTrace()
                single.onError(t)
            }
        })
        return single
    }

    override suspend fun getAllPokemonFromDb(): Observable<List<PokemonEntity>> {
        return Observable.just(pokemonDao.getAll())
    }

    override suspend fun getPokemonFromDb(id: String, name: String): Observable<List<PokemonEntity>> {
        return Observable.just(pokemonDao.getByIdOrName(id, name))
    }
}