package com.pokemon.data.service

import com.pokemon.data.model.api.Pokemon
import com.pokemon.data.model.api.webhook.WebHookResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface  WebHookService {
    @POST("cf6b43a8-9a5e-44a5-870a-349f87c61d91/pokemon/catch")
    fun catchPokemon(@Body pokemon: Pokemon) : Single<WebHookResponse>

    @POST("cf6b43a8-9a5e-44a5-870a-349f87c61d91/pokemon/favorite")
    fun favoritePokemon(@Body pokemon: Pokemon) : Single<WebHookResponse>
}