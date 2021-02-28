package com.pokemon.data.repository

import com.pokemon.data.model.api.Pokemon
import com.pokemon.data.model.api.webhook.WebHookResponse
import io.reactivex.Single

interface WebHookRepository {
    fun catchPokemon(pokemon: Pokemon) : Single<WebHookResponse>
}