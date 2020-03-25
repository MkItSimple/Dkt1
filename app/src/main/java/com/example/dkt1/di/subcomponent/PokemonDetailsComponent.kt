package com.example.dkt1.di.subcomponent

import com.example.dkt1.di.module.PokemonDetailsViewModelModule
import com.example.dkt1.di.module.ViewModelFactoryModule
import com.example.dkt1.di.scope.FragmentScope
import com.example.dkt1.ui.PokemonDetailsFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(
    modules = [
        ViewModelFactoryModule::class,
        PokemonDetailsViewModelModule::class
    ]
)
interface PokemonDetailsComponent {
    fun inject(pokemonDetailsFragment: PokemonDetailsFragment)
}