package com.example.dkt1.di.component

import com.example.dkt1.di.module.NetworkModule
import com.example.dkt1.di.module.PokemonUsecaseModule
import com.example.dkt1.di.module.RepositoryModule
import com.example.dkt1.di.scope.AppScope
import com.example.dkt1.di.subcomponent.PokemonDetailsComponent
import com.example.dkt1.di.subcomponent.PokemonListComponent
import dagger.Component

@AppScope
@Component(
    modules = [
        NetworkModule::class,
        PokemonUsecaseModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent {
    fun newPokemonLisComponent(): PokemonListComponent
    fun newPokemonDetailsComponent(): PokemonDetailsComponent
}