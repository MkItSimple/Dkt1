package com.example.dkt1.di.module

import com.example.dkt1.di.scope.AppScope
import com.example.dkt1.network.PokemonApi
import com.example.dkt1.repository.PokemonRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @AppScope
    @Provides
    fun provideFeedRepository(api: PokemonApi) = PokemonRepository(api)
}