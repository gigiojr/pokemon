package com.pokemon.data.di

import android.content.Context
import com.pokemon.data.network.NetworkInfo
import com.pokemon.data.network.NetworkInfoImpl
import com.pokemon.data.service.PokemonService
import com.pokemon.data.service.WebHookService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun bindNetworkInfo(@ApplicationContext context: Context): NetworkInfo =
            NetworkInfoImpl(context)

    @Singleton
    @Provides
    fun providesPokemonApiAccess(): PokemonService {
        val client = OkHttpClient
            .Builder()
            .build()

        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(PokemonService::class.java)
    }

    @Singleton
    @Provides
    fun webHookApiAccess(): WebHookService {
        val uid = "cf6b43a8-9a5e-44a5-870a-349f87c61d91"

        val client = OkHttpClient
            .Builder()
            .build()

        return Retrofit.Builder()
            .baseUrl("https://webhook.site/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(WebHookService::class.java)
    }
}