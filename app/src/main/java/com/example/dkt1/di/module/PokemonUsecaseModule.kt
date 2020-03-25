package com.example.dkt1.di.module

import com.example.dkt1.di.scope.AppScope
import com.example.dkt1.domain.PokemonUsecase
import com.example.dkt1.repository.PokemonRepository
import dagger.Module
import dagger.Provides

@Module
class PokemonUsecaseModule {
    @AppScope
    @Provides
    fun provideFeedUseCase(repository :PokemonRepository) = PokemonUsecase(repository)
}