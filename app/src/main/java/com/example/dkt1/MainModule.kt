package com.example.dkt1

import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    fun providesInfo(): MainActivity.Info {
        return MainActivity.Info("Love Dagger 2")
    }
}