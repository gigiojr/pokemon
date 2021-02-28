package com.pokemon.data.repository

import com.pokemon.data.model.api.Pokemon
import com.pokemon.data.model.api.webhook.WebHookResponse
import com.pokemon.data.service.WebHookService
import io.reactivex.Single
import javax.inject.Inject

class WebHookRepositoryImp @Inject constructor(private val service: WebHookService) : WebHookRepository {
    override fun catchPokemon(pokemon: Pokemon) : Single<WebHookResponse> {
        return service.catchPokemon(pokemon)
    }
}