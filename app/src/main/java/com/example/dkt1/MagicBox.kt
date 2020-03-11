package com.example.dkt1

import dagger.Component
import dagger.Provides
import javax.inject.Inject

@Component(modules = [MainModule::class])
interface MagicBox {
    fun poke(app: MainActivity)
}